package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config.properties")
public interface ServerConfig extends Config {
    String login();
    String pass();
    String name();
    String nameLatin();
    String surname();
    String surnameLatin();
    String nickname();
    String birthday();
    String telegram();
    String skype();
}
