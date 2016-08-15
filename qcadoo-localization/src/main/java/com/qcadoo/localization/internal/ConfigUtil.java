package com.qcadoo.localization.internal;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConfigUtil {

    @Value("${hotDeploy}")
    private boolean hotDeploy;

    @Value("${sourceBasePath}")
    private String sourceBasePath;

    public boolean isHotDeploy() {
        return hotDeploy;
    }

    public String getSourceBasePath() {
        return Strings.isNullOrEmpty(sourceBasePath) ? getDefaultSourceBasePath() : sourceBasePath;
    }

    public String getDefaultSourceBasePath() {
        if (isHotDeploy()) {
            String currentDir = getClass().getClassLoader().getResource("").getPath();
            String currentDirSuffix = "/mes/mes-application/target/tomcat-archiver/mes-application/webapps/ROOT/WEB-INF/classes/";

            return currentDir.substring(0, currentDir.length() - currentDirSuffix.length());
            
        } else {
            return "";
        }
    }
}
