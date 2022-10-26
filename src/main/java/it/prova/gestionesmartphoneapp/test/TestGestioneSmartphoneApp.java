package it.prova.gestionesmartphoneapp.test;

import it.prova.gestionesmartphoneapp.dao.EntityManagerUtil;
import it.prova.gestionesmartphoneapp.model.App;
import it.prova.gestionesmartphoneapp.model.Smartphone;
import it.prova.gestionesmartphoneapp.service.MyServiceFactory;
import it.prova.gestionesmartphoneapp.service.app.AppService;
import it.prova.gestionesmartphoneapp.service.smartphone.SmartphoneService;

public class TestGestioneSmartphoneApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SmartphoneService smartphoneServiceInstance = MyServiceFactory.getSmartphoneServiceInstance();
		AppService appServiceIstance = MyServiceFactory.getAppServiceInstance();

		try {

			System.out.println(
					"In tabella Smartphone ci sono " + smartphoneServiceInstance.listAll().size() + " elementi.");
			System.out.println("In tabella App ci sono " + appServiceIstance.listAll().size() + " elementi.");

			System.out.println(
					"**************************** inizio batteria di test ********************************************");
			System.out.println(
					"*************************************************************************************************");

			testCRUDSmartphone(smartphoneServiceInstance, appServiceIstance);

			System.out.println(
					"****************************** fine batteria di test ********************************************");
			System.out.println(
					"*************************************************************************************************");

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			EntityManagerUtil.shutdown();
		}

	}

	private static void testCRUDSmartphone(SmartphoneService smartphoneServiceInstance, AppService appServiceIstance)
			throws Exception {

		// TODO Auto-generated method stub
		System.out.println(".......testCRUDSmartphone inizio.............");

		// Inserimento Nuovo Smartphone
		Smartphone smartphone = new Smartphone("Apple", "iPhone 13 Pro Max", 1499, "iOS15");
		smartphoneServiceInstance.inserisciNuovo(smartphone);
		if (smartphone.getId() == null)
			throw new RuntimeException("testInserimentoNuovoSmartphone FAILED");

		// Aggiornamento versioneOS di uno Smartphone esistente
		smartphone.setVersioneOS("iOS16");
		smartphoneServiceInstance.aggiorna(smartphone);
		if (smartphoneServiceInstance.caricaSingoloElemento(smartphone.getId()).getVersioneOS()
				.equals(smartphone.getVersioneOS()))
			throw new RuntimeException("testAggiornamentoVersioneOS FAILED");

		// Inserimento Nuova App - Instagram
		App appInstagram = new App("Instagram", "256.0");
		appServiceIstance.inserisciNuovo(appInstagram);
		if (appInstagram.getId() == null)
			throw new RuntimeException("testInserimentoNuovaApp FAILED");

		// Inserimento Nuova App - Facebook
		App appFacebook = new App("Facebook", "390.1");
		appServiceIstance.inserisciNuovo(appInstagram);
		if (appInstagram.getId() == null)
			throw new RuntimeException("testInserimentoNuovaApp FAILED");

		// Aggiornamento versione App con relativa data
		appInstagram.setVersione("257.0");
		if (appServiceIstance.caricaSingoloElemento(appInstagram.getId()).getVersione()
				.equals(appInstagram.getVersione()))
			throw new RuntimeException("testAggiornamentoVersione FAILED");

		// Installazione Instagram
		smartphoneServiceInstance.aggiungiApp(smartphone, appInstagram);
		if (smartphone.getApps().isEmpty())
			throw new RuntimeException("testInstallazioneAppLatoSmartphone FAILED");
		if (appInstagram.getSmartphones().isEmpty())
			throw new RuntimeException("testInstallazioneAppLatoApp FAILED");
		
		//Installazione Facebook
		smartphoneServiceInstance.aggiungiApp(smartphone, appFacebook);
		if (smartphone.getApps().isEmpty())
			throw new RuntimeException("testInstallazioneAppLatoSmartphone FAILED");
		if (appInstagram.getSmartphones().isEmpty())
			throw new RuntimeException("testInstallazioneAppLatoApp FAILED");
		
		//Disinstallazione App
		smartphoneServiceInstance.rimuoviApp(smartphone, appInstagram);
		if (smartphone.getApps().contains(appInstagram))
			throw new RuntimeException("testDisinstallazioneApp FAILED");
		if (appInstagram.getSmartphones().contains(smartphone))
			throw new RuntimeException("testDisinstallazioneApp FAILED");

		// Rimozione Smarthphone
		smartphoneServiceInstance.rimuovi(smartphone.getId());
		if (smartphoneServiceInstance.caricaSingoloElemento(smartphone.getId()) != null)
			throw new RuntimeException("testRimozioneSmartphone FAILED");

		// Rimozione App - Instagram
		appServiceIstance.rimuovi(appInstagram.getId());
		if (appServiceIstance.caricaSingoloElemento(appInstagram.getId()) != null)
			throw new RuntimeException("testRimozioneApp FAILED");

		// Rimozione App - Facebook
		appServiceIstance.rimuovi(appFacebook.getId());
		if (appServiceIstance.caricaSingoloElemento(appFacebook.getId()) != null)
			throw new RuntimeException("testRimozioneApp FAILED");

		System.out.println(".......testCRUDSmartphone fine: PASSED.............");

	}

}
