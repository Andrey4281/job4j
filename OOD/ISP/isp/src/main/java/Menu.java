import com.google.common.base.Joiner;

import java.util.*;

public class Menu {
    private static final String LN = System.getProperty("line.separator");

    private static class MenuItem {
        private final String name;
        private int levelOfTree;
        private final Handler handler;
        private List<MenuItem> childs = new LinkedList<>();


        private MenuItem(String name, Handler handler, int levelOfTree) {
            this.name = name;
            this.handler = handler;
            this.levelOfTree = levelOfTree;
        }

        private void addChild(MenuItem item) {
            childs.add(item);
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < levelOfTree * 4; i++)
                res.append("-");
            return res.append(" ").append(name).toString();
        }
    }

    private final String rootName = "headOfMenu";
    private final Map<String, MenuItem> menuItemMap = new HashMap<>();
    private final Console console;

    public Menu(Console console) {
        this.console = console;
        this.menuItemMap.put(rootName, new MenuItem(rootName, null, -1));
    }

    public void addItemOfMenu(String nameParent, String name, Handler handler) {
        MenuItem parent;
        if (nameParent == null) {
            parent = menuItemMap.get(rootName);
        } else {
            parent = menuItemMap.get(nameParent);
            if (parent == null) {
                throw new InvalidMenuOperationException(Joiner.on(LN)
                        .join("Invalid argument exception.", String.format("Parent with name: %s does not exist.", nameParent), ""));
            }
        }
        MenuItem temp = new MenuItem(name, handler, parent.levelOfTree + 1);
        parent.addChild(temp);
        menuItemMap.put(name, temp);
    }

    public void showMenu() {
        LinkedList<Iterator<MenuItem>> stack = new LinkedList<>();
        stack.addFirst(menuItemMap.get(rootName).childs.iterator());
        while (!stack.isEmpty()) {
            if (stack.peek().hasNext()) {
                MenuItem item = stack.peek().next();
                stack.addFirst(item.childs.iterator());
                console.output(item.toString());
            } else {
                stack.removeFirst();
            }
        }
    }

    public void executeOperation(String menuItem) {
        MenuItem item = menuItemMap.get(menuItem);
        if (item == null) {
            throw new InvalidMenuOperationException(Joiner.on(LN)
                    .join("Invalid argument exception.", String.format("MenuItem with name: %s does not exist.", menuItem), ""));
        }
        item.handler.handle(console);
    }

    public static void main(String[] args) {
        Menu menu = new Menu(new Console(new Input(System.in), new Output(System.out)));
        menu.addItemOfMenu(null, "Задача 1.", null);
        menu.addItemOfMenu(null, "Задача 2.", null);
        menu.addItemOfMenu("Задача 1.", "Задача 1.1.", (console)->{
            console.output("Введите Ваше имя");
            String name = console.input();
        console.output("Привет " + name);});
        menu.addItemOfMenu("Задача 2.", "Задача 2.1.", null);
        menu.addItemOfMenu("Задача 2.", "Задача 2.2.", null);
        menu.addItemOfMenu("Задача 1.1.", "Задача 1.1.1.", null);
        menu.addItemOfMenu("Задача 1.1.", "Задача 1.1.2.", null);
        menu.addItemOfMenu("Задача 1.", "Задача 1.2.", null);
        menu.showMenu();
        menu.executeOperation("Задача 1.1.");
    }
}
