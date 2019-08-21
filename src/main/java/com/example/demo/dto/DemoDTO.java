package com.example.demo.dto;

import com.example.demo.constants.DemoA.DemoAConst;
import lombok.Data;

@Data
public class DemoDTO {

    private String status;

    public void setStatus(String status) {
        int i = Integer.parseInt(status);
        switch (i) {
            case 1 : this.status = DemoAConst.aConst.DEMO_A1.getValue();break;
            case 2 : this.status = DemoAConst.aConst.DEMO_A2.getValue();break;
            case 3 : this.status = DemoAConst.aConst.DEMO_A3.getValue();break;
            default: this.status = status;
        }
    }
}
