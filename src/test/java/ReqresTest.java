import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.xml.bind.annotation.XmlElementDecl;

import static org.hamcrest.Matchers.*;

public class ReqresTest {
    private String USERS_API = "api/users?page=2";
    private String POST_USERS_API = "api/users";
    private String PUT_USERS_API = "api/users/2";
    private String DELETE_USERS_API = "api/users/2";
    private StepHelper stepHelper;
    private int userID;

    @BeforeClass
    public void setup() {
        this.stepHelper = new StepHelper();
    }

    @Test
    public void Test_01() {
        Response response = RestAssured.get(GlobalVariables.SERVER_URL + USERS_API);
        stepHelper.showReceivedData(response.getBody().asString());
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());

        Assert.assertEquals(response.getStatusCode(), GlobalVariables.STATUS_CODE_200);
    }

    @Test
    public void Test_02() {
        RestAssured.given().get(GlobalVariables.SERVER_URL + USERS_API)
                .then()
                .statusCode(200)
                .body("data.id[0]", equalTo(7));

    }

    @Test
    public void Test_03_get() {
        RestAssured.given()
                .contentType(GlobalVariables.CONTENT_TYPE)
                .get(GlobalVariables.SERVER_URL + USERS_API)
                .then()
                .body("data.first_name", hasItems("Michael", "Lindsay"));
    }

    @Test(priority = 1)
    public void Test_04_post() {
        Response response = RestAssured.given()
                .contentType(GlobalVariables.CONTENT_TYPE)
                .body(BodyType.userParams())
                .when()
                .post(GlobalVariables.SERVER_URL + POST_USERS_API);

        Assert.assertEquals(GlobalVariables.STATUS_CODE_201, response.statusCode());

    }

    @Test(priority = 2)
    public void Test_05_put() {
        Response response = RestAssured.given()
                .contentType(GlobalVariables.CONTENT_TYPE)
                .body(BodyType.userParams())
                .when()
                .put(GlobalVariables.SERVER_URL + PUT_USERS_API);

        Assert.assertEquals(GlobalVariables.STATUS_CODE_200, response.statusCode());
    }

    @Test(priority = 3)
    public void Test_06_delete() {
        Response response = RestAssured.given()
                .contentType(GlobalVariables.CONTENT_TYPE)
                .body(BodyType.userParams())
                .when()
                .delete(GlobalVariables.SERVER_URL + DELETE_USERS_API);

        Assert.assertEquals(GlobalVariables.STATUS_CODE_204, response.statusCode());
    }
}
