import io.qameta.allure.Step;

public class StepHelper {

    @Step("Sent data: ")
    public void showSentData(String data){
        System.out.println(data);
    }

    @Step("Received data: ")
    public void showReceivedData(String response){
        System.out.println(response);
    }
}
