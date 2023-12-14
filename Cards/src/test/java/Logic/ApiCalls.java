package Logic;

import java.io.IOException;
import java.util.HashMap;

import infrastructure.HttpFacade;
import infrastructure.HttpResponse;
public class ApiCalls {
    private static final String BASE_URL = "https://deckofcardsapi.com/api/deck/";

//    public ApiCalls() {
 //   }

    public static HttpResponse createDeck() throws IOException {
        String url = BASE_URL + "new/";
        return HttpFacade.get(url, new HashMap<>(), null);
    }
    public static HttpResponse shuffleDeckCards(String deckId,boolean remaining) throws IOException {
        String url = BASE_URL + deckId+ "/shuffle/";
        if (remaining) {
            url += "?remaining=true";
        }
        return HttpFacade.get(url, new HashMap<>(), null);
    }

    public static HttpResponse drawCards(String deck_id, int cardsToDraw) throws IOException {
        String url = BASE_URL + deck_id + "/draw/?count="+cardsToDraw;
        return HttpFacade.get(url,new HashMap<>(),null);
    }
    public static HttpResponse createPileAddDrawnCards(String deck_id, String pile_name, String listOfCards)throws IOException{
        String url = BASE_URL + deck_id + "/pile/"+ pile_name+ "/add/"+"?cards="+listOfCards;
        return HttpFacade.get(url,new HashMap<>(),null);
    }
    public static HttpResponse listCardsinPile(String deck_id, String pile_name)throws IOException {
        String url = BASE_URL + deck_id + "/pile/" + pile_name + "/list/";
        return HttpFacade.get(url, new HashMap<>(), null);
    }
}
