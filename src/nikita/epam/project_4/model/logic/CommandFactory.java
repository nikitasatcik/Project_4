package nikita.epam.project_4.model.logic;

import java.util.HashMap;
import java.util.Map;

import nikita.epam.project_4.model.dao.DAOFactory;

public class CommandFactory {
	private Map<String, Command> commands;
	private static CommandFactory instance;
	
	private CommandFactory() {
		commands = new HashMap<>();

		Command.setDAOFactory(DAOFactory.getDAOFactory("MYSQL"));
		
		// create commands and set mapping
		commands.put("user_login", new UserLoginCommand());
		commands.put("admin_login", new AdminLoginCommand());
		commands.put("create_user", new CreateUserCommand());
		commands.put("update_users", new UpdateUsersCommand());
		commands.put("logout", new LogoutCommand());
		commands.put("find_all_tours", new GetAllToursCommand());
	}
	
	public static synchronized CommandFactory getInstance() {
		if (instance == null) {
			instance = new CommandFactory();
		}
		return instance;
	}
	
	public Command getCommand(String id) {
		return commands.get(id);
	}
}