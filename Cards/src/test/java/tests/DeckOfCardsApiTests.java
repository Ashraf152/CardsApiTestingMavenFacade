package tests;

import Logic.ApiCalls;
import Logic.ApiResponse;
import Logic.ApiResponseParser;
import infrastructure.HttpResponse;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(AllureJunit5.class)
public class DeckOfCardsApiTests {
    private static String deckID;
    private static  HttpResponse<ApiResponse> result = null;
    private static final String pileName="Ashraf";

    @BeforeAll
    public static void beforAll() throws IOException{
        // arr
        result = ApiCalls.createDeck();
        result.setData(getJsonData(result.getData()));
        deckID=result.getData().getDeck_id();
    }

    @Test
    public void createDeckTest() {
        //Assert
        assertTrue(result.getData().isSuccess());
        assertEquals(200,result.getStatus());
        assertEquals(52,result.getData().getRemaining());
    }

    @Test
    public void shuffleDeckCardsTest() throws IOException {
        //Act
        result = ApiCalls.shuffleDeckCards(deckID, false);
        result.setData(getJsonData(result.getData()));

        // Assert
        assertEquals(result.getStatus(), 200);
        assertTrue(result.getData().isSuccess());
        assertTrue(result.getData().isShuffled());

    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 4 })
    public void drawCardsTest(int cardsToDraw) {
        try {
            // Arrange and act
            result = ApiCalls.drawCards(deckID, cardsToDraw);
            result.setData(getJsonData(result.getData()));
            // Assert
            assertEquals(result.getData().getCards().size(), cardsToDraw);
            assertEquals(result.getStatus(), 200);

        } catch (IOException e) {
            // Handle or log the IOException
            e.printStackTrace();
            // You may choose to fail the test or take appropriate action based on your requirements
            fail("IOException occurred during test: " + e.getMessage());
        }
    }

    @Test
    public void createPileAddDrawnCardsTest() {
        // Arrange
        final String listOfDrawnCards = "5S,6H,AS";

        try {
            // Act
            result = ApiCalls.createPileAddDrawnCards(deckID, pileName, listOfDrawnCards);
            result.setData(getJsonData(result.getData()));

            // Assert
            assertEquals(result.getStatus(), 200);
            assertNotNull(result.getData().getPiles(), "Pile is null.");
        } catch (IOException e) {
            // Handle IOException
            e.printStackTrace(); // Log or handle the exception according to your needs
            fail("IOException occurred during the test: " + e.getMessage());
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace(); // Log or handle the exception according to your needs
            fail("An unexpected exception occurred during the test: " + e.getMessage());
        }
    }


    @Test
    public void listCardsInPileTest() {
        try {
            //Act
            result = ApiCalls.listCardsinPile(deckID, pileName);
            result.setData(getJsonData(result.getData()));
            Map<String, ApiResponse.Pile> pileList = result.getData().getPiles();
            // Assert
            assertEquals(result.getStatus(), 200);
            // Check for null to avoid NullPointerException
            for (Map.Entry<String, ApiResponse.Pile> entry : pileList.entrySet()) {
                ApiResponse.Pile pile = entry.getValue();
                assertEquals(pile.getRemaining(), pile.getCards().size());
            }
        } catch (IOException e) {
            // Handle or log the IOException
            e.printStackTrace();
            // You may choose to fail the test or take appropriate action based on your requirements
            fail("IOException occurred during test: " + e.getMessage());
        }
    }
    @AfterAll
    public static void destroyResultObject() {
        // Destroy or release resources associated with 'result' object
        result = null;
    }

    private static ApiResponse getJsonData(Object data){
        String jsonData=String.valueOf(data);
        return ApiResponseParser.parseJson(String.valueOf(jsonData));
    }

    /* IMPORTANT NOTE:
type: "npx allure-commandline serve" in terminal to open a website, viewing report results
*/

}
