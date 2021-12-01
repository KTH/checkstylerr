package com.bsuir.balashenka.command.impl;

import com.bsuir.balashenka.command.Command;
import com.bsuir.balashenka.controller.ClientController;
import com.bsuir.balashenka.controller.impl.TcpClientController;
import com.bsuir.balashenka.exception.AvailableTokenNotPresentException;
import com.bsuir.balashenka.exception.WrongCommandFormatException;
import com.bsuir.balashenka.service.Connection;
import com.bsuir.balashenka.util.AvailableToken;

import java.util.Arrays;
import java.util.Map;

public class ExitCommand extends BaseCommand {
    private ClientController clientController;

    public ExitCommand() {
        Arrays.stream(AvailableToken.values()).forEach(t -> getAvailableTokens().put(t.getName(), t.getRegex()));
        this.clientController = TcpClientController.getInstance();
    }

    @Override
    public void execute() {
        try {
            validateTokens();
            checkTokenCount();
            Connection connection = clientController.getConnection();
            Map<String, String> tokens = getTokens();
            if (tokens.size() > 0) {
                String firstKey = String.valueOf(getTokens().keySet().toArray()[0]);
                AvailableToken currentToken = AvailableToken.find(firstKey);

                switch (currentToken) {
                    case FORCE:
                        executeForceExit();
                        break;
                    case HELP:
                        executeHelp();
                        break;
                }
            } else {
                if (connection == null) {
                    TcpClientController.getInstance().getKeyboard().wantExit(true);
                } else {
                    System.out.println("Connection is opened. Please, close connection to terminate program.");
                }
            }
        } catch (WrongCommandFormatException | AvailableTokenNotPresentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Command build() {
        return new ExitCommand();
    }

    private void checkTokenCount() throws WrongCommandFormatException {
        if (getTokens().size() > 1) {
            throw new WrongCommandFormatException("This command should have only one token.");
        }
    }

    private void executeForceExit() {
        Connection tcpConnection = clientController.getConnection();
        if (tcpConnection != null) {
            tcpConnection.close();
        }
        TcpClientController.getInstance().getKeyboard().wantExit(true);
    }

    private void executeHelp() {
        System.out.println("Command format:");
        System.out.println("   exit [-force] [-help]");
    }
}
