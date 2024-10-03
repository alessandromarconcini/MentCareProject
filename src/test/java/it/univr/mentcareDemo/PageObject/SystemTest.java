package it.univr.mentcareDemo.PageObject;

import it.univr.mentcareDemo.model.*;
import it.univr.mentcareDemo.model.repository.*;
import org.apache.commons.lang3.SystemUtils;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)  // test in sequenza

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SystemTest {

    private WebDriver driver;

    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private ReceptionistRepository receptionistRepository;
    @Autowired
    private NurseRepository nurseRepository;
    private Long testManagerId;
    @Autowired
    private DrugDepositRepository drugDepositRepository;

    @Before
    public void setUp() {
        org.openqa.selenium.chrome.ChromeOptions chrome_options = new ChromeOptions();
       chrome_options.addArguments("--headless");
        if (SystemUtils.IS_OS_WINDOWS) {
            System.setProperty("webdriver.chrome.driver",
                    Paths.get("src/test/resources/chromedriver_win32_966/chromedriver.exe").toString());
        } else if (SystemUtils.IS_OS_MAC) {
            System.setProperty("webdriver.chrome.driver",
                    Paths.get("src/test/resources/chromedriver_mac64_m1_96/chromedriver").toString());
        } else if (SystemUtils.IS_OS_LINUX) {
            System.setProperty("webdriver.chrome.driver",
                    Paths.get("src/test/resources/chromedriver_linux64_96/chromedriver").toString());
        }
        if (driver == null)
            driver = new ChromeDriver(chrome_options);

    }

    /**
     * Test di creazione dell'entità manager necessaria per i test
     * @return
     */

    public Long testManagerCreation(){

        testDefaultDrugDepositCreation();

        Manager test = new Manager("testName","testSurname","testPassword","testFiscalCodeFF","testBirthPlace","testBirth");
        managerRepository.save(test);

        return managerRepository.findByName("testName").getId();
    }

    public void testDefaultDrugDepositCreation(){

        DrugDeposit tachipirina;

        if(drugDepositRepository.findByName("Tachipirina") == null) {
            tachipirina = new DrugDeposit("Tachipirina", 100F, 5000F);
            drugDepositRepository.save(tachipirina);
        }
    }

    public void testManagerDeletion(){ managerRepository.deleteAll();}
    public void testDoctorDeletion(){ doctorRepository.deleteAll();}
    public void testPatientDeletion(){ patientRepository.deleteAll();}
    public void testReceptionistDeletion(){ receptionistRepository.deleteAll();}
    public void testNurseDeletion(){ nurseRepository.deleteAll();}

    /**
     * Page object tests
     */
    // Il Manager effettua il login per registrare gli attori
    //TODO: il prof ha detto di testare una sola funzionalità per pagina, alcuni test possiamo
    // levarli in caso di problemi. Fare più test aumenta la Coverage.

    @Test
    public void testA_loginManager() {

        driver.get("http://localhost:8080");

        LoginPageObject login = new LoginPageObject(driver);

        assertEquals("Login Clinica", login.getTitleLogin());

        testManagerId = testManagerCreation();
        //Recupero il manager appena creato dal database
        Manager testManager = managerRepository.findById(testManagerId).get();

        login.enterUsername(""+testManager.getId());
        login.enterPassword(""+testManager.getPassword());
        PageObject loginPO = login.submit();

        ManagerPageObject manager = new ManagerPageObject(driver);
        assertEquals("Home Manager", manager.getTitleHomeManager());

    }
    // Da ora si iniziano a creare gli attori
    @Test
    public void testB_AddDoctor() {

        // ho effettuato il login con manager e sono nella sua schermata

        driver.get("http://localhost:8080/homeManager?id=2");

        ManagerPageObject managerPO = new ManagerPageObject(driver);
        assertEquals("Home Manager", managerPO.getTitleHomeManager());

        // clicco su "create Doctor"
        CreateDoctorPO createDoctorPO = new CreateDoctorPO(driver);
        managerPO  = createDoctorPO.createDoctor();

        // Verifico che sono nella pagina di creazione dottore
       // createDoctorPO doctorPO = new createDoctorPO(driver);
        assertEquals("Registrazione Medico", createDoctorPO.getTitleCreationDoctor());

        // ora inizio a compilare i campi richiesti
        createDoctorPO.enterNameDoctor("Pippo");
        createDoctorPO.enterSurnameDoctor("Pluto");
        createDoctorPO.enterPasswordDoctor("aa");
        createDoctorPO.enterBirthDoctor("10/12/1980");
        createDoctorPO.enterBirthPlaceDoctor("Palermo");
        createDoctorPO.enterFiscalCodeDoctor("dfgshjkkshjsfgse");
        createDoctorPO.enterPhoneNumberDoctor("5662145650");
        createDoctorPO.enterSpecializationDoctor("Urologo");

        // clicco registra
        createDoctorPO.BottomRegistration();

        // Verifico che i campi inseriti siano corretti
        assertEquals("Pippo", managerPO.getLastUserNameInTable());
        assertEquals("Pluto", managerPO.getLastUserSurnameInTable());
        assertEquals("dfgshjkkshjsfgse", managerPO.getLastUserFiscalCodeInTable());
        assertEquals("Doctor", managerPO.getLastUserTypeInTable());


        // Una volta creato, verifico di essere ritornato alla home
        assertEquals("Home Manager", managerPO.getTitleHomeManager());

        //testDoctorDeletion(Long.parseLong(managerPO.getUserId()));
    }

    @Test
    public void testC_AddPatient() {

        // ho effettuato il login con manager e sono nella sua schermata

        driver.get("http://localhost:8080/homeManager?id=2");

        ManagerPageObject managerPO = new ManagerPageObject(driver);
        assertEquals("Home Manager", managerPO.getTitleHomeManager());

        // clicco su "create Patient"
        CreatePatientPO createPatientPO = new CreatePatientPO(driver);
        managerPO  = createPatientPO.createPatient();

        // Verifico che sono nella pagina di creazione paziente
        // createDoctorPO doctorPO = new createDoctorPO(driver);
        assertEquals("Registrazione Paziente", createPatientPO.getTitleHomePatient());

        //Compilo i campi di patient
        createPatientPO.enterNamePatient("Pippo");
        createPatientPO.enterSurnamePatient("Pluto");
        createPatientPO.enterPasswordPatient("aa");
        createPatientPO.enterBirthPatient("10/12/1980");
        createPatientPO.enterBirthPlacePatient("Palermo");
        createPatientPO.enterFiscalCodePatient("dfgshjkkshjsfgse");
        createPatientPO.enterPhoneNumberPatient("5662145650");
        createPatientPO.enterPathologyPatient("Bipolarismo");
        //Questo valore nella view viene inserito tramite un bottone, quindi faccio click su pericoloso
        createPatientPO.radioButtonDangerous();

        // clicco registra
        createPatientPO.BottomRegistration();

        assertEquals("Home Manager", managerPO.getTitleHomeManager());

        // Verifico che i campi inseriti siano corretti
        assertEquals("Pippo", managerPO.getLastUserNameInTable());
        assertEquals("Pluto", managerPO.getLastUserSurnameInTable());
        assertEquals("dfgshjkkshjsfgse", managerPO.getLastUserFiscalCodeInTable());
        assertEquals("Patient", managerPO.getLastUserTypeInTable());
    }


    @Test
    public void testD_AddNurse() {

        // ho effettuato il login con manager e sono nella sua schermata

        driver.get("http://localhost:8080/homeManager?id=2");

        ManagerPageObject managerPO = new ManagerPageObject(driver);
        assertEquals("Home Manager", managerPO.getTitleHomeManager());

        // clicco su "create Patient"
        CreateNursePO createNursePO = new CreateNursePO(driver);
        managerPO  = createNursePO.createNurse();

        // Verifico che sono nella pagina di creazione paziente
        // createDoctorPO doctorPO = new createDoctorPO(driver);
        assertEquals("Registrazione Infermiere", createNursePO.getTitleHomeNurse());

        //Compilo i campi di patient
        createNursePO.enterNameNurse("Pippo");
        createNursePO.enterSurnameNurse("Pluto");
        createNursePO.enterPasswordNurse("aa");
        createNursePO.enterBirthNurse("10/12/1980");
        createNursePO.enterBirthPlaceNurse("Palermo");
        createNursePO.enterFiscalCodeNurse("dfgshjkkshjsfgse");

        // clicco registra
        createNursePO.BottomRegistration();

        // Verifico che i campi inseriti siano corretti
        assertEquals("Pippo", managerPO.getLastUserNameInTable());
        assertEquals("Pluto", managerPO.getLastUserSurnameInTable());
        assertEquals("dfgshjkkshjsfgse", managerPO.getLastUserFiscalCodeInTable());
        assertEquals("Nurse", managerPO.getLastUserTypeInTable());


        // Una volta creato, verifico di essere ritornato alla home
        assertEquals("Home Manager", managerPO.getTitleHomeManager());
        //testNurseDeletion(Long.parseLong(managerPO.getUserId()));
    }
    @Test
    public void testE_AddReceptionist() {

        // ho effettuato il login con manager e sono nella sua schermata

        driver.get("http://localhost:8080/homeManager?id=2");

        ManagerPageObject managerPO = new ManagerPageObject(driver);
        assertEquals("Home Manager", managerPO.getTitleHomeManager());

        // clicco su "create Patient"
        CreateReceptionistPO createReceptionistPO = new CreateReceptionistPO(driver);
        managerPO  = createReceptionistPO.createReceptionist();

        // Verifico che sono nella pagina di creazione paziente
        // createDoctorPO doctorPO = new createDoctorPO(driver);
        assertEquals("Registrazione Receptionist", createReceptionistPO.getTitleHomeReceptionist());

        //Compilo i campi di patient
        createReceptionistPO.enterNameReceptionist("Pippo");
        createReceptionistPO.enterSurnameReceptionist("Pluto");
        createReceptionistPO.enterPasswordReceptionist("aa");
        createReceptionistPO.enterBirthReceptionist("10/12/1980");
        createReceptionistPO.enterBirthPlaceReceptionist("Palermo");
        createReceptionistPO.enterFiscalCodeReceptionist("dfgshjkkshjsfgse");

        // clicco registra
        createReceptionistPO.BottomRegistration();

        // Verifico che i campi inseriti siano corretti
        assertEquals("Pippo", managerPO.getLastUserNameInTable());
        assertEquals("Pluto", managerPO.getLastUserSurnameInTable());
        assertEquals("dfgshjkkshjsfgse", managerPO.getLastUserFiscalCodeInTable());
        assertEquals("Receptionist", managerPO.getLastUserTypeInTable());


        // Una volta creato, verifico di essere ritornato alla home
        assertEquals("Home Manager", managerPO.getTitleHomeManager());

     //   testReceptionistDeletion(Long.parseLong(managerPO.getUserId()));


    }
    // rimango nella schermata del manager e faccio il check sulla tabella

    @Test
    public void testF_SearchUserByManager() {

        driver.get("http://localhost:8080/homeManager?id=2");

        SearchUserByManager search = new SearchUserByManager(driver);
        ManagerPageObject managerPO = new ManagerPageObject(driver);

        search.searchBar();
        search.enterSurname("Pluto");

        search.buttonToSearch();

        for (int i = 0; managerPO.getNumOfUserInTable() < i; i++){
            assertEquals("Pluto", managerPO.getUserInTable(i)); //Perché la ricerca restituisce tutti i pazienti nel registro appuntamenti, non solo 1
        }
        //assertEquals("Pluto", managerPO.getLastUserSurnameInTable());

        search.clear();

        //logout Manager

        LoginPageObject logout = new LoginPageObject(driver);
        managerPO = logout.logoutManager();

        assertEquals("Login Clinica", logout.getTitleLogin());
    }

    //Test Receptionist
    @Test
    public void testG_CreateAppointmentByReceptionist() {

        driver.get("http://localhost:8080");

        LoginPageObject login = new LoginPageObject(driver);

        assertEquals("Login Clinica", login.getTitleLogin());

        Long receptionistId = null;
        String receptionistPassword = null;

        //Prendo i dati di un receptionist dal repository
        for (Receptionist receptionist: receptionistRepository.findAll()){
            receptionistId = receptionist.getId();
            receptionistPassword = receptionist.getPassword();
        }

        //Effettuo il login con un receptionist
        login.enterUsername(""+receptionistId);
        login.enterPassword(""+receptionistPassword);
        PageObject loginPO = login.submit();

        ReceptionistPageObject receptionistPO = new ReceptionistPageObject(driver);

        assertEquals("Home Receptionist", receptionistPO.getTitleHomeReceptionist());

        CreateAppointmentByReceptionist createAppointmentPO = new CreateAppointmentByReceptionist(driver);
        receptionistPO  = createAppointmentPO.createAppointment();

        assertEquals("Inserimento Appuntamenti", createAppointmentPO.getTitleCreateAppointment());

        Long patientId = null;


        for (Patient patient: patientRepository.findAll()){
            patientId = patient.getId();
        }

        createAppointmentPO.enterPatientId(""+patientId);
        createAppointmentPO.enterPatientName("Pippo");
        createAppointmentPO.enterPatientSurname("Pluto");
        createAppointmentPO.enterDoctorOfPatient();
        createAppointmentPO.enterNurseOfPatient();
        createAppointmentPO.enterAppointmentDate("15/02/2015");
        createAppointmentPO.enterAppointmentHour("12:15");

        createAppointmentPO.registerNewAppointment();
        //Ritorno alla home receptionist
        assertEquals("Home Receptionist", receptionistPO.getTitleHomeReceptionist());

        //check per vedere se i dati inseriti sono corretti
        assertEquals(patientId+"", receptionistPO.getTablePatientId());
        assertEquals("Pluto", receptionistPO.getTablePatientSurname());
        assertEquals("Pluto", receptionistPO.getTableDoctorOfPatient());
        assertEquals("Pluto", receptionistPO.getTableNurseOfPatient());
        assertEquals("2015-02-15", receptionistPO.getTableAppointmentDate());
        assertEquals("12:15", receptionistPO.getTableAppointmentHour());

    }

    @Test
    public void testH_UpdateAppointmentByReceptionist(){

        driver.get("http://localhost:8080/homeReceptionist?id=6");

        Long patientId = null;

        //Prendo i dati di un receptionist dal repository
        for (Patient patient: patientRepository.findAll()){
            patientId = patient.getId();
        }

        ReceptionistPageObject receptionistPO = new ReceptionistPageObject(driver);
        UpdateAppointmentByReceptionist updateAppointmentByReceptionist = new UpdateAppointmentByReceptionist(driver);
        receptionistPO = updateAppointmentByReceptionist.modifyAppointment();

        assertEquals("Modifica Appuntamento", updateAppointmentByReceptionist.getTitleModifyAppointment());
        updateAppointmentByReceptionist.enterPatientId(""+ patientId);
        updateAppointmentByReceptionist.enterPatientName("Pluto");
        updateAppointmentByReceptionist.enterPatientSurname("Pluto");
        updateAppointmentByReceptionist.enterDoctorOfPatient();
        updateAppointmentByReceptionist.enterNurseOfPatient();
        updateAppointmentByReceptionist.enterAppointmentDate("01/01/2023");
        updateAppointmentByReceptionist.enterAppointmentHour("09.00");

        updateAppointmentByReceptionist.confirmModify();

        //Ritorno alla home receptionist
        assertEquals("Home Receptionist", receptionistPO.getTitleHomeReceptionist());

        //check per vedere se i dati inseriti sono corretti
        assertEquals(patientId+"", receptionistPO.getTablePatientId());
        assertEquals("Pluto", receptionistPO.getTablePatientSurname());
        assertEquals("Pluto", receptionistPO.getTableDoctorOfPatient());
        assertEquals("Pluto", receptionistPO.getTableNurseOfPatient());
        assertEquals("2023-01-01", receptionistPO.getTableAppointmentDate());
        assertEquals("09.00", receptionistPO.getTableAppointmentHour());

        //logout Receptionist
        LoginPageObject logout = new LoginPageObject(driver);
        receptionistPO = logout.logoutReceptionist();

        assertEquals("Login Clinica", logout.getTitleLogin());
    }

    @Test
    public void testI_SearchAppointmentByReceptionist() {

        driver.get("http://localhost:8080/homeReceptionist?id=6");

        SearchPatientByReceptionist search = new SearchPatientByReceptionist(driver);
        ReceptionistPageObject receptionistPO = new ReceptionistPageObject(driver);


        assertEquals("Home Receptionist", receptionistPO.getTitleHomeReceptionist());


        search.searchBar();
        search.enterSurname("Pluto");

        search.buttomToSearch();

        for (int i = 0; receptionistPO.getNumOfUserInTable() < i; i++){
            assertEquals("Pluto", receptionistPO.getAllAppointmentInTable(i)); //Perché la ricerca restituisce tutti i pazienti nel registro appuntamenti, non solo 1
        }
        //assertEquals("Pluto", managerPO.getLastUserSurnameInTable());

        search.clear();

        //logout Receptionist
        LoginPageObject logout = new LoginPageObject(driver);
        receptionistPO = logout.logoutReceptionist();

        assertEquals("Login Clinica", logout.getTitleLogin());
    }

    @Test
    public void testL_SearchPatientByDoctor() {

        driver.get("http://localhost:8080");

        LoginPageObject login = new LoginPageObject(driver);

        assertEquals("Login Clinica", login.getTitleLogin());

        Long doctorId = null;
        String doctorPassword = null;

        //Prendo i dati di un receptionist dal repository
        for (Doctor doctor: doctorRepository.findAll()){
            doctorId = doctor.getId();
            doctorPassword = doctor.getPassword();
        }

        //Effettuo il login con un receptionist
        login.enterUsername(""+doctorId);
        login.enterPassword(""+doctorPassword);
        PageObject loginPO = login.submit();

        DoctorPageObject doctorPO = new DoctorPageObject(driver);

        assertEquals("Home Doctor", doctorPO.getTitleHomeDoctor());

        SearchPatientByDoctor search = new SearchPatientByDoctor(driver);


        search.searchBar();
        search.enterSurname("Pluto");

        search.buttomToSearch();

        for (int i = 0; doctorPO.getNumOfUserInTable() < i; i++){
            assertEquals("Pluto", doctorPO.getAllAppointmentInTable(i)); //Perché la ricerca restituisce tutti i pazienti nel registro appuntamenti, non solo 1
        }
        //assertEquals("Pluto", managerPO.getLastUserSurnameInTable());

        search.clear();

    }


    @Test
    public void testM_CreateAndViewPrescription() {

        testDefaultDrugDepositCreation();

        driver.get("http://localhost:8080");

        LoginPageObject login = new LoginPageObject(driver);

        assertEquals("Login Clinica", login.getTitleLogin());

        Long doctorId = null;
        String doctorPassword = null;

        //Prendo i dati di un receptionist dal repository
        for (Doctor doctor: doctorRepository.findAll()){
            doctorId = doctor.getId();
            doctorPassword = doctor.getPassword();
        }

        //Effettuo il login con un receptionist
        login.enterUsername(""+doctorId);
        login.enterPassword(""+doctorPassword);
        PageObject loginPO = login.submit();

        DoctorPageObject doctorPO = new DoctorPageObject(driver);

        assertEquals("Home Doctor", doctorPO.getTitleHomeDoctor());

        // Clicco sul bottone "crea prescrizione"
        CreatePrescription newPrescriptionPO = new CreatePrescription(driver);
        doctorPO  = newPrescriptionPO.createPrescription();

        assertEquals("Nuova Prescrizione Medica", newPrescriptionPO.getTitleCreatePrescription());

        // Mi cerco il paziente a cui devo fare la prescrizione
        Long patientId = null;

        for (Patient patient: patientRepository.findAll()){
            patientId = patient.getId();
        }

        newPrescriptionPO.enterPatientId("" + patientId);

        newPrescriptionPO.enterDrug();
        newPrescriptionPO.enterDose("200.0");
        newPrescriptionPO.enterFrequency("3 volte al giorno");
        newPrescriptionPO.enterTextReport("Seguire le indicazioni");

        // Salvo la prescrizione
        newPrescriptionPO.registerNewPrescription();

        assertEquals("Home Doctor", doctorPO.getTitleHomeDoctor());

        // Adesso bisogna verificare che le informazioni inserite siano corrette


        doctorPO.selectRow();

        // vado su "Visualizza prescrizione"
        ViewPatientPrescriptionByDoctor patientPrescriptionPO = new ViewPatientPrescriptionByDoctor(driver);
        PageObject view  = patientPrescriptionPO.viewPrescription();

        assertEquals("Prescrizione Medica Paziente", patientPrescriptionPO.getTitlePatientPrescription());

        // Verifico che i campi inseriti precedentemente dal dottore siano corretti
        assertEquals("Seguire le indicazioni", patientPrescriptionPO.getTextPrescription());

        assertEquals("Tachipirina", patientPrescriptionPO.getTableDrugName());
        assertEquals("200.0", patientPrescriptionPO.getTableDrugAssignedDose());
        assertEquals("3 volte al giorno", patientPrescriptionPO.getTableDrugFrequency());

        // Una volta verificato, clicco su indietro per tornare alla homepage
        view = patientPrescriptionPO.backword();
        assertEquals("Home Doctor", doctorPO.getTitleHomeDoctor());


        //ora posso fare il logout
        LoginPageObject logout = new LoginPageObject(driver);
        doctorPO = logout.logoutDoctor();

        assertEquals("Login Clinica", logout.getTitleLogin());

    }


    @Test
    public void testN_SearchAppointmentByNurse() {

        driver.get("http://localhost:8080");

        LoginPageObject login = new LoginPageObject(driver);

        assertEquals("Login Clinica", login.getTitleLogin());

        Long nurseId = null;
        String nursePassword = null;

        //Prendo i dati di un receptionist dal repository
        for (Nurse nurse: nurseRepository.findAll()){
            nurseId = nurse.getId();
            nursePassword = nurse.getPassword();
        }

        //Effettuo il login con un receptionist
        login.enterUsername(""+nurseId);
        login.enterPassword(""+nursePassword);
        PageObject loginPO = login.submit();

        SearchPatientByNurse search = new SearchPatientByNurse(driver);
        NursePageObject nursePO = new NursePageObject(driver);

        assertEquals("Home Nurse", nursePO.getTitleHomeNurse());

        search.searchBar();
        search.enterSurname("Pluto");

        search.buttomToSearch();

        for (int i = 0; nursePO.getNumOfUserInTable() < i; i++){
            assertEquals("Pluto", nursePO.getAllAppointmentInTable(i)); //Perché la ricerca restituisce tutti i pazienti nel registro appuntamenti, non solo 1
        }
        //assertEquals("Pluto", managerPO.getLastUserSurnameInTable());

        search.clear();
         }


    @Test
    public void testO_ViewPrescriptionByNurse() {

        driver.get("http://localhost:8080");

        LoginPageObject login = new LoginPageObject(driver);

        assertEquals("Login Clinica", login.getTitleLogin());

        Long nurseId = null;
        String nursePassword = null;

        //Prendo i dati di un receptionist dal repository
        for (Nurse nurse: nurseRepository.findAll()){
            nurseId = nurse.getId();
            nursePassword = nurse.getPassword();
        }

        //Effettuo il login con un receptionist
        login.enterUsername(""+nurseId);
        login.enterPassword(""+nursePassword);
        PageObject loginPO = login.submit();

        NursePageObject nursePO = new NursePageObject(driver);

        assertEquals("Home Nurse", nursePO.getTitleHomeNurse());

        ViewPatientPrescriptionByNurse patientPrescriptionPO = new ViewPatientPrescriptionByNurse(driver);
        nursePO = patientPrescriptionPO.viewPrescription();

        assertEquals("Prescrizione Medica Paziente", patientPrescriptionPO.getTitlePatientPrescription());

        // Verifico che i campi inseriti precedenttemente dal dottore siano corretti
        assertEquals("Seguire le indicazioni", patientPrescriptionPO.getTextPrescription());
        assertEquals("Tachipirina", patientPrescriptionPO.getDrugName());
        assertEquals("200.0", patientPrescriptionPO.getDrugAssignedDose());
        assertEquals("3 volte al giorno", patientPrescriptionPO.getDrugFrequency());

        // Una volta verificato, clicco su indietro per tornare alla homepage
        nursePO = patientPrescriptionPO.backword();
        assertEquals("Home Nurse", nursePO.getTitleHomeNurse());


        //ora posso fare il logout
        LoginPageObject logout = new LoginPageObject(driver);
        nursePO = logout.logoutNurse();

        assertEquals("Login Clinica", logout.getTitleLogin());

    }

    @Test
    public void testP_ViewPrescriptionByPatient() {

        driver.get("http://localhost:8080");

        LoginPageObject login = new LoginPageObject(driver);

        assertEquals("Login Clinica", login.getTitleLogin());

        Long patientId = null;
        String patientPassword = null;

        //Prendo i dati di un receptionist dal repository
        for (Patient patient: patientRepository.findAll()){
            patientId = patient.getId();
            patientPassword = patient.getPassword();
        }

        //Effettuo il login con un receptionist
        login.enterUsername(""+patientId);
        login.enterPassword(""+patientPassword);
        PageObject loginPO = login.submit();

        PatientPageObject patientPO = new PatientPageObject(driver);
        assertEquals("Home Patient", patientPO.getTitleHomePatient());

        ViewPrescriptionByPatient patientPrescriptionPO = new ViewPrescriptionByPatient(driver);
        patientPO = patientPrescriptionPO.viewPrescription();

        assertEquals("Prescrizione Medica Paziente", patientPrescriptionPO.getTitlePatientPrescription());

        // Verifico che i campi inseriti precedenttemente dal dottore siano corretti
        assertEquals("Seguire le indicazioni", patientPrescriptionPO.getTextPrescription());
        assertEquals("Tachipirina", patientPrescriptionPO.getDrugName());
        assertEquals("200.0", patientPrescriptionPO.getDrugAssignedDose());
        assertEquals("3 volte al giorno", patientPrescriptionPO.getDrugFrequency());

        // Una volta verificato, clicco su indietro per tornare alla homepage
        patientPO = patientPrescriptionPO.backword();
        assertEquals("Home Patient", patientPO.getTitleHomePatient());


        //ora posso fare il logout
        LoginPageObject logout = new LoginPageObject(driver);
        patientPO = logout.logoutPatient();

        assertEquals("Login Clinica", logout.getTitleLogin());

    }

    @Test
    public void testQ_CheckAppointmentByPatient() {

        driver.get("http://localhost:8080");

        LoginPageObject login = new LoginPageObject(driver);

        assertEquals("Login Clinica", login.getTitleLogin());

        Long patientId = null;
        String patientPassword = null;

        //Prendo i dati di un receptionist dal repository
        for (Patient patient: patientRepository.findAll()){
            patientId = patient.getId();
            patientPassword = patient.getPassword();
        }
        Long appointmentId = null;
        for (Appointment appointment: appointmentRepository.findAll()){
            appointmentId = appointment.getId();
           }

        //Effettuo il login con un receptionist
        login.enterUsername(""+patientId);
        login.enterPassword(""+patientPassword);
        PageObject loginPO = login.submit();

        PatientPageObject patientPO = new PatientPageObject(driver);

        assertEquals("Home Patient", patientPO.getTitleHomePatient());

        // Verifico che i campi inseriti precedentemente dal dottore siano corretti
        assertEquals(""+appointmentId, patientPO.getIdAppointment()); // id dell'appuntamento
        assertEquals("Pippo", patientPO.getPatientName());
        assertEquals("Pluto", patientPO.getPatientSurname());
        assertEquals("Pluto", patientPO.getDoctorOfThePatient());
        assertEquals("2023-01-01", patientPO.getAppointmentDate());
        assertEquals("09.00", patientPO.getAppointmentHour());

        //ora posso fare il logout
        LoginPageObject logout = new LoginPageObject(driver);
        patientPO = logout.logoutPatient();

        assertEquals("Login Clinica", logout.getTitleLogin());

    }

    @Test
    public void testR_CheckAppointmentByNurse() {

        driver.get("http://localhost:8080");

        LoginPageObject login = new LoginPageObject(driver);

        assertEquals("Login Clinica", login.getTitleLogin());

        Long nurseId = null;
        String nursePassword = null;

        //Prendo i dati di un receptionist dal repository
        for (Nurse nurse: nurseRepository.findAll()){
            nurseId = nurse.getId();
            nursePassword = nurse.getPassword();
        }
        Long appointmentId = null;
        for (Appointment appointment: appointmentRepository.findAll()){
            appointmentId = appointment.getId();
        }
        Long patientId = null;
        for (Patient patient: patientRepository.findAll()){
            patientId = patient.getId();
        }

        //Effettuo il login con un receptionist
        login.enterUsername(""+nurseId);
        login.enterPassword(""+nursePassword);
        PageObject loginPO = login.submit();

        NursePageObject nursePO = new NursePageObject(driver);

        assertEquals("Home Nurse", nursePO.getTitleHomeNurse());

        // Verifico che i campi inseriti precedentemente dal dottore siano corretti
        assertEquals(""+patientId, nursePO.getIdPatient()); // id dell'appuntamento
        assertEquals("Pippo", nursePO.getPatientName());
        assertEquals("Pluto", nursePO.getPatientSurname());
        assertEquals("Pluto", nursePO.getDoctorOfThePatient());
        assertEquals("2023-01-01", nursePO.getAppointmentDate());
        assertEquals("09.00", nursePO.getAppointmentHour());

        //ora posso fare il logout
        LoginPageObject logout = new LoginPageObject(driver);
        nursePO = logout.logoutNurse();

        assertEquals("Login Clinica", logout.getTitleLogin());

    }

    @Test
    public void testS_CheckAppointmentByDoctor() {

        driver.get("http://localhost:8080");

        LoginPageObject login = new LoginPageObject(driver);

        assertEquals("Login Clinica", login.getTitleLogin());

        Long doctorId = null;
        String doctorPassword = null;

        //Prendo i dati di un receptionist dal repository
        for (Doctor doctor: doctorRepository.findAll()){
            doctorId = doctor.getId();
            doctorPassword = doctor.getPassword();
        }

        //Effettuo il login con un receptionist
        login.enterUsername(""+doctorId);
        login.enterPassword(""+doctorPassword);
        PageObject loginPO = login.submit();

        DoctorPageObject doctorPO = new DoctorPageObject(driver);

        assertEquals("Home Doctor", doctorPO.getTitleHomeDoctor());

        //Prendo i dati di un receptionist dal repository

        Long appointmentId = null;
        for (Appointment appointment: appointmentRepository.findAll()){
            appointmentId = appointment.getId();
        }
        Long patientId = null;
        for (Patient patient: patientRepository.findAll()){
            patientId = patient.getId();
        }

        // Verifico che i campi inseriti precedentemente dal dottore siano corretti
        assertEquals(""+patientId, doctorPO.getPatientId()); // id dell'appuntamento
        assertEquals("Pippo", doctorPO.getPatientName());
        assertEquals("Pluto", doctorPO.getPatientSurname());
        assertEquals("Pluto", doctorPO.getNurseSurname());
        assertEquals("2023-01-01", doctorPO.getAppointmentDate());
        assertEquals("09.00", doctorPO.getAppointmentHour());

        //ora posso fare il logout
        LoginPageObject logout = new LoginPageObject(driver);
        doctorPO = logout.logoutDoctor();

        assertEquals("Login Clinica", logout.getTitleLogin());

    }

    @Test
    public void testT_DeleteAppointment() {

       driver.get("http://localhost:8080");

        LoginPageObject login = new LoginPageObject(driver);

        assertEquals("Login Clinica", login.getTitleLogin());

        Long receptionistId = null;
        String receptionistPassword = null;

        Long appointmentId = null;

        //Prendo i dati di un receptionist dal repository
        for (Receptionist receptionist: receptionistRepository.findAll()){
            receptionistId = receptionist.getId();
            receptionistPassword = receptionist.getPassword();
        }
        for (Appointment appointment: appointmentRepository.findAll()){
            appointmentId = appointment.getId();
        }

        //Effettuo il login con un receptionist
        login.enterUsername(""+receptionistId);
        login.enterPassword(""+receptionistPassword);
        PageObject loginPO = login.submit();

        ReceptionistPageObject receptionistPO = new ReceptionistPageObject(driver);

        assertEquals("Home Receptionist", receptionistPO.getTitleHomeReceptionist());

        DeleteAppointmentPO deletePO = new DeleteAppointmentPO(driver);

        deletePO.deleteAppointment();

        assertEquals(0, receptionistPO.getLastAppointmentSizeInTable());

        //logout Receptionist
        LoginPageObject logout = new LoginPageObject(driver);
        receptionistPO = logout.logoutReceptionist();

        assertEquals("Login Clinica", logout.getTitleLogin());

    }


    @Test
    public void testU_DeleteAllUser()
    {
        testDoctorDeletion();
        testPatientDeletion();
        testNurseDeletion();
        testManagerDeletion();
    }





    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

        //testManagerDeletion(testManagerId);
    }

}
