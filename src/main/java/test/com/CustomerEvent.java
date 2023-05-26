package test.com;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerEvent {

    private String affiliationCode;
    private String account;
    private String producerId;
    private String pageUrl;

}
