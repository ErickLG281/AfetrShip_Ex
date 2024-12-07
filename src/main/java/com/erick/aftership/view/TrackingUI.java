package com.erick.aftership.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import org.asynchttpclient.*;


public class TrackingUI extends JFrame {
    private JTextField trackingNumberField;
    private JTextArea resultArea;

    public TrackingUI() {
        setTitle("Package Tracker");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        trackingNumberField = new JTextField(20);
        JButton trackButton = new JButton("Track Package");
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);

        trackButton.addActionListener(e -> {
            String trackingNumber = trackingNumberField.getText().trim();
            if (!trackingNumber.isEmpty()) {
                try {
                    trackPackage(trackingNumber);  // Llama al método para rastrear el paquete
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                resultArea.setText("Please enter a tracking number.");
            }
        });

        panel.add(new JLabel("Enter Tracking Number:"), BorderLayout.NORTH);
        panel.add(trackingNumberField, BorderLayout.CENTER);
        panel.add(trackButton, BorderLayout.SOUTH);
        panel.add(new JScrollPane(resultArea), BorderLayout.EAST);

        add(panel);
    }

    private void trackPackage(String trackingNumber) throws IOException {
        AsyncHttpClient client = new DefaultAsyncHttpClient();

        String url = "https://postal-ninja.p.rapidapi.com/v1/track/" + trackingNumber + "?await=false&lang=AS_IS";

        client.prepare("GET", url)
                .setHeader("x-rapidapi-key", "1bf9d47a11msh4e757c15199be00p11aaf0jsn26a453afdcd6")
                .setHeader("x-rapidapi-host", "postal-ninja.p.rapidapi.com")
                .setHeader("Accept", "application/json; charset=UTF-8")
                .execute()
                .toCompletableFuture()
                .thenAccept(response -> {
                    // Muestra la respuesta en el JTextArea
                    resultArea.setText(response.getResponseBody());
                })
                .join();

        client.close();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TrackingUI().setVisible(true));
    }
}