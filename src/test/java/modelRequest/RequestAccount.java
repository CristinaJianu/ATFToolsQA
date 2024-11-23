package modelRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestAccount {
    private String userName;
    private String password;

    public RequestAccount(String userName, String password) {
        this.userName = userName;
        this.password = password;
        implemetBusinessRules();
    }
    private void implemetBusinessRules(){
        userName=userName+System.currentTimeMillis();
    }
}
