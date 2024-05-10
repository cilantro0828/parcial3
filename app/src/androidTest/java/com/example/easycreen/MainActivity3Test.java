package com.example.easycreen;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivity3Test {

    private UiDevice mDevice;

    @Before
    public void setUp() throws Exception {
        // Inicializar UiDevice
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    }

    @Test
    public void testAddButtonClick() {
        // Configurar una actividad de escenario
        ActivityScenario<MainActivity3> activityScenario = ActivityScenario.launch(MainActivity3.class);

        // Esperar a que la actividad se lance completamente
        Espresso.onView(withId(R.id.corre)).perform(typeText("example@example.com"));
        Espresso.onView(withId(R.id.contrasenaa)).perform(typeText("password"));
        Espresso.onView(withId(R.id.conf_contraa)).perform(typeText("password"));

        // Simular un clic en el botón btn_add
        Espresso.onView(withId(R.id.btn_add)).perform(click());

        // Aquí podrías agregar más verificaciones según lo que esperas que haga el botón de agregar
    }
}