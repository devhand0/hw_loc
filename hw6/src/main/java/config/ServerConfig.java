package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config.properties")
public interface ServerConfig extends Config {
@Config.Key("name")
    String name();
    String nameLatin();
    String surname();
    String surnameLatin();
    String nickname();
    String birthday();
    String telegram();
    String skype();
}
