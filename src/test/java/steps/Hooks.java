package steps;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.junit.Before;
import utils.Log;


public class Hooks {

    @Before
    public void beforeTest(Scenario scenario) {
        Log.info("___ Старт сценария '"+ scenario.getName() + " ___");

    }

    @After
    public void afterTest(Scenario scenario){
        if(scenario.isFailed()){
            Log.warn("___Сценарий '" + scenario.getName() +"' провалился___" );
        }else {
            Log.info("___Cценарий '" + scenario.getName() + "' завершился успешно___");
        }
    }


}
