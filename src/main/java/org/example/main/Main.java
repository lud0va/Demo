package org.example.main;

import org.example.ComponenteFechaClase;
import org.example.exceptions.FechaException;
import org.example.exceptions.FechaImposibeException;
import org.example.exceptions.FechaIncompletaException;
import org.example.exceptions.FechaIncorrectaException;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ComponenteFechaClase com = new ComponenteFechaClase();
            ComponenteFechaClase com2 = new ComponenteFechaClase();
            JButton getDateButton = new JButton("Get Date");
            JFrame frame = new JFrame("org.example.ComponenteFechaImp Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new FlowLayout());
            frame.add(com);
            frame.add(com2);
            frame.add(getDateButton);

            frame.pack();
            frame.setVisible(true);

            getDateButton.addActionListener(e -> {
                LocalDate date1 = null;
                LocalDate date2 = null;
                try {
                    date1 = com.getDate();
                } catch (FechaIncompletaException ex) {
                    JOptionPane.showMessageDialog(frame, "El componente 1 esta incompleto");
                } catch (FechaImposibeException ex) {
                    JOptionPane.showMessageDialog(frame, "El componente 1 es imposible");
                } catch (FechaIncorrectaException ex) {
                    JOptionPane.showMessageDialog(frame,"El componente 1 es incorrecto");
                } catch (FechaException ex) {
                    throw new RuntimeException(ex);
                }
                try {

                    date2 = com2.getDate();
                    if(date2 != null && date1!=null){
                        JOptionPane.showMessageDialog(frame,  comprobarFechas(date1,date2,frame)+" dias");

                    }
                } catch (FechaIncompletaException ex) {

                    JOptionPane.showMessageDialog(frame, "El componente 2 esta incompleto");
                } catch (FechaIncorrectaException ex) {
                    JOptionPane.showMessageDialog(frame, "El componente 2 es incorrecto");
                } catch (FechaImposibeException ex) {
                    JOptionPane.showMessageDialog(frame,"El componente 2 es imposible");
                } catch (FechaException ex) {
                    throw new RuntimeException(ex);
                }


            });
        });
    }

    public static int comprobarFechas(LocalDate date, LocalDate date2, Frame frame) {
        if (date.compareTo(date2) > 0) {
            JOptionPane.showMessageDialog(frame, "Order incorrecto");

        } else {
            return (int) ChronoUnit.DAYS.between(date, date2);

        }
        return 0;
    }

}

