package kz.daracademy.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OktaUserInfo {
    private String sub;
    private String name;
    private String locale;
    private String email;
    private String nickname;
    private String preferred_username;
    private String given_name;
    private String middle_name;
    private String family_name;
    private String zoneinfo;
    private long updated_at;
    private boolean email_verified;

}


/*
* {
    "sub": "00u2gfl6q3Lb3i2d6357",
    "name": "Yerkebulan Yessenov",
    "locale": "en-US",
    "email": "YeYessenov@dar.kz",
    "nickname": "",
    "preferred_username": "YeYessenov@dar.kz",
    "given_name": "Yerkebulan",
    "middle_name": "",
    "family_name": "Yessenov",
    "zoneinfo": "America/Los_Angeles",
    "updated_at": 1589884430,
    "email_verified": true
}*/
