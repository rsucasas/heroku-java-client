package seaclouds.paas.adapter.heroku_deploy;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import com.heroku.sdk.deploy.DeployWar;

public class XDeployWar extends DeployWar {

    String apiKey;
    
    public XDeployWar(String name, File warFile, URL webappRunnerUrl, String apiKey) throws IOException {
        super(name, warFile, webappRunnerUrl);
        this.deployer.setEncodedApiKey(apiKey);
    }
}