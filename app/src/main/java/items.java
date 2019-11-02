public class items {


    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    private String timing;
        private String session;

        public items() {
            super();
        }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public items(String timing, String session) {
        super();
        this.timing = timing;
        this.session = session;

    }


}
