package com.friendlygeek.goldenrental.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Driver {
    private static final Logger logger = LogManager.getLogger("com.friendlygeek.goldenrental");

    public static void main(String[] args) {
        GoldenBikeRentalApp app = new GoldenBikeRentalApp();

        if (!app.Initialize()) {
            logger.error("Could not initialize system. Shutting down");
            System.exit(-1);
        }

        app.Run();
    }


}
