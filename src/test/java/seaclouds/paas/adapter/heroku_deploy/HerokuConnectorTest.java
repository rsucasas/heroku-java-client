package seaclouds.paas.adapter.heroku_deploy;

import static org.junit.Assert.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.heroku.api.Addon;


/**
 * 
 * @author
 *
 */
public class HerokuConnectorTest
{
	
	
	// logs
	private static final Logger logAdapter = Logger.getLogger(HerokuConnectorTest.class.getName());
	
	// heroku client
	public HerokuConnector hclient;
	
	// global values for tests
	public static final String APP_NAME = "app-tests-wabapp-roi2";
	public static final String APP_WAR_PATH = "C:/temporal/webapp.war";
	public static final String ADDON_CLEARDB_IGNITE = "cleardb:ignite";
	public static final String ENV_CLEARDB = "CLEARDB_DATABASE_URL";
	
	
	@Before
	public void setUp() throws Exception
	{
		hclient = new HerokuConnector();
	}
	
	
	@After
	public void tearDown() throws Exception
	{
		hclient.deleteApp(APP_NAME);
		
		logAdapter.log(Level.INFO, ">> closing connection...");
		if (hclient != null)
			hclient.getHerokuAPIClient().getConnection().close();
	}
	
	
	@Test
	public void testHerokuConnector()
	{
		assertTrue(hclient != null);
	}
	
	
	@Test
	public void testCreateJavaWebApp()
	{
		assertTrue(hclient.createJavaWebApp(APP_NAME) != null);
	}
	
	
	@Test
	public void testDeleteApp()
	{
		hclient.createJavaWebApp(APP_NAME);
		hclient.deleteApp(APP_NAME);
		
		assertTrue(!hclient.appExists(APP_NAME));
	}
	
	/*
	@Test
	public void testDeployApp()
	{
		assertTrue(hclient.deployJavaWebApp(APP_NAME, APP_WAR_PATH));
	}
	*/
	
	@Test
	public void testDeployJavaWebAppWithDataBase()
	{
		hclient.deployJavaWebAppWithDataBase(APP_NAME, APP_WAR_PATH, ADDON_CLEARDB_IGNITE);
		
		assertTrue(hclient.getAppEnvironmentValue(APP_NAME, ENV_CLEARDB) != null);
	}
	
	
	@Test
	public void testGetAddonByName()
	{
		Addon add = hclient.getAddonByName(ADDON_CLEARDB_IGNITE);
		
		assertTrue(add != null);
	}
	
	
	@Test
	public void testCreateJavaWebAppWithClearDB()
	{
		hclient.createJavaWebApp(APP_NAME);
		hclient.addAddonToApp(APP_NAME, ADDON_CLEARDB_IGNITE);
		
		assertTrue(hclient.getAppEnvironmentValue(APP_NAME, ENV_CLEARDB) != null);
	}
	

}
