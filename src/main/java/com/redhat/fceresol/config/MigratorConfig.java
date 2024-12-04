package com.redhat.fceresol.config;


import io.quarkus.runtime.annotations.StaticInitSafe;
import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;
import io.smallrye.config.WithName;

@StaticInitSafe
@ConfigMapping(prefix = "threescale")
public interface MigratorConfig {

    @WithName("exportToken")
    String exportToken();
    
    @WithName("importToken")
    String importToken();
    
    @WithName("forceDeploymentOption")
    @WithDefault("")
    String forceDeploymentOption();
    @WithName("proxyPromoteToProduction")
    @WithDefault("false")
    Boolean proxyPromoteToProduction();

}