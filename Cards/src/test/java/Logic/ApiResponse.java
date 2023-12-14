package Logic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;



public class ApiResponse {
    private boolean success;
    private String deck_id;
    private List<Card> cards;
    private boolean shuffled;
    private int remaining;
    private Map<String, Pile> piles;
    // Additional fields that may vary


    @JsonCreator
    public ApiResponse(@JsonProperty("success") boolean success,
                       @JsonProperty("deck_id") String deck_id,
                       @JsonProperty("cards") List<Card> cards,
                       @JsonProperty("shuffled") boolean shuffled,
                       @JsonProperty("remaining") int remaining,
                       @JsonProperty("piles") Map<String, Pile> piles) {
        this.success = success;
        this.deck_id = deck_id;
        this.cards = cards;
        this.shuffled = shuffled;
        this.remaining = remaining;
        this.piles = piles;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getDeck_id() {
        return deck_id;
    }

    public void setDeck_id(String deck_id) {
        this.deck_id = deck_id;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public boolean isShuffled() {
        return shuffled;
    }

    public void setShuffled(boolean shuffled) {
        this.shuffled = shuffled;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public Map<String, Pile> getPiles() {
        return piles;
    }

    public void setPiles(Map<String, Pile> piles) {
        this.piles = piles;
    }


    public static class Pile {

        private List<Card> cards;
        private int remaining;
        @JsonCreator
        public Pile(@JsonProperty("cards") List<Card> cards,
                    @JsonProperty("remaining") int remaining) {
            this.cards = cards;
            this.remaining = remaining;
        }

        public List<Card> getCards() {
            return cards;
        }

        public void setCards(List<Card> cards) {
            this.cards = cards;
        }

        public int getRemaining() {
            return remaining;
        }

        public void setRemaining(int remaining) {
            this.remaining = remaining;
        }

        @Override
        public String toString() {
            return "Pile{" +
                    "cards=" + cards +
                    ", remaining='" + remaining + '\'' +
                    '}';
        }
    }


    public static class Card {

        private String code;
        private String image;
        private Images images;
        private String value;
        private String suit;
        @JsonCreator
        public Card(@JsonProperty("code") String code,
                    @JsonProperty("image") String image,
                    @JsonProperty("images") Images images,
                    @JsonProperty("value") String value,
                    @JsonProperty("suit") String suit) {
            this.code = code;
            this.image = image;
            this.images = images;
            this.value = value;
            this.suit = suit;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public Images getImages() {
            return images;
        }

        public void setImages(Images images) {
            this.images = images;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getSuit() {
            return suit;
        }

        public void setSuit(String suit) {
            this.suit = suit;
        }

        @Override
        public String toString() {
            return "Card{" +
                    "code='" + code + '\'' +
                    ", image='" + image + '\'' +
                    ", images=" + images +
                    ", value='" + value + '\'' +
                    ", suit='" + suit + '\'' +
                    '}';
        }
    }

    public static class Images {

        private String svg;
        private String png;
        @JsonCreator
        public Images(@JsonProperty("svg") String svg,
                      @JsonProperty("png") String png) {
            this.svg = svg;
            this.png = png;
        }

        public String getSvg() {
            return svg;
        }

        public void setSvg(String svg) {
            this.svg = svg;
        }

        public String getPng() {
            return png;
        }

        public void setPng(String png) {
            this.png = png;
        }

        @Override
        public String toString() {
            return "Images{" +
                    "svg='" + svg + '\'' +
                    ", png='" + png + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "success=" + success +
                ", deck_id='" + deck_id + '\'' +
                ", cards=" + cards +
                ", shuffled=" + shuffled +
                ", remaining=" + remaining +
                ", piles=" + piles +
                '}';
    }
}
