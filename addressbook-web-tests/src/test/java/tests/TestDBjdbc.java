package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestDBjdbc {
    protected static ApplicationManager app;

    @BeforeEach
    public void setUp() {
        if (app == null)
            app = new ApplicationManager();
    }

    @Test
    public void testQuery() {
        app.jdbc().testQuerySelect();
    }

    @Test
    public void testQuery2() {
        app.jdbc().testQuerySelect();
        app.jdbc().testQueryUpdate();
        app.jdbc().testQuerySelect();
    }

    @Test
    public void testQuery3() {
        app.jdbc().testQueryInsert();
    }

}
