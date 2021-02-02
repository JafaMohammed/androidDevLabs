package com.mojaafar.volleylab;

public class DeveloperList
{

    private String login;
    private String avatar_url;
    private String html_url;

    public DeveloperList(String login, String git_url, String avatar_url){
        this.login=login;
        this.avatar_url=avatar_url;
        this.html_url=git_url;

    }
    public String getLogin(){
    return login;

    }
    public String getAvatar_url(){
        return avatar_url;

    }
    public String getHtml_url(){
        return html_url;
    }
}
