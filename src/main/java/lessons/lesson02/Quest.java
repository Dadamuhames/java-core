package lessons.lesson02;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Quest {
    private final Scanner scanner;
    private State state;
    private boolean isRunning;
    private Map<State, Runnable> scenes;

    enum State {
        S_1, S_2A, S_2B, S_2C, S_3A, S_3B, S_3C, S_3D, S_3E, S_3F, S_4A, S_4B
    }

    public Quest(Scanner scanner) {
        this.scanner = scanner;
        this.state = State.S_1;
        this.isRunning = true;
        this.scenes = new HashMap<>();

        scenes.put(State.S_1, sceneOne());
        scenes.put(State.S_2A, sceneTwoA());
        scenes.put(State.S_2B, sceneTwoB());
        scenes.put(State.S_2C, sceneTwoC());
        scenes.put(State.S_3A, sceneThreeA());
        scenes.put(State.S_3B, sceneThreeB());
        scenes.put(State.S_3C, sceneThreeC());
        scenes.put(State.S_3D, sceneThreeD());
        scenes.put(State.S_3E, sceneThreeE());
        scenes.put(State.S_3F, sceneThreeF());
        scenes.put(State.S_4A, sceneFourA());
        scenes.put(State.S_4B, sceneFourB());
    }

    public void run() {
        while (isRunning) {
            scenes.get(this.state).run();
        }
    }

    public Runnable sceneOne() {
        return () -> {
            System.out.println("Ты приходишь в себя на холодном песке у берега озера. Вокруг — туман, ночь, ни души. Телефон в кармане почти разряжен (1%).\n" + "В другом кармане — спичка и клочок бумаги с надписью:\n" + "\"Не доверяй голосам. Иди к северу.\"\n");

            System.out.println("1 - \uD83D\uDD26 Пойти на восток, туда, где в тумане мерцает свет.");
            System.out.println("2 - \uD83E\uDDED Пойти на север, как советует записка.");
            System.out.println("3 - \uD83E\uDEB5 Остаться у озера, может, кто-то придёт.");

            int choice = getChoice(3);

            switch (choice) {
                case 1 -> state = State.S_2A;

                case 2 -> state = State.S_2B;

                case 3 -> state = State.S_2C;
            }
        };
    }


    private Runnable sceneTwoA() {
        return () -> {
            System.out.println("\n📍 Сцена 2A: Восточный свет");
            System.out.println("Ты идёшь на свет и находишь старую электростанцию. Внезапно загорается прожектор.\n" + "Голос в громкоговорителе: «Стоять. Идентификация...\"\n" + "Ты ничего не понимаешь.\n");
            System.out.println("Выбор:");
            System.out.println("1 - 🤖 Ответить: «Я человек, я потерялся!»");
            System.out.println("2 - 🏃 Убежать назад в лес.");

            int choice = getChoice(2);
            state = (choice == 1) ? State.S_3A : State.S_3B;
        };
    }

    private Runnable sceneTwoB() {
        return () -> {
            System.out.println("\n📍 Сцена 2B: Северный путь");
            System.out.println("Ты следуешь на север и находишь заброшенную деревню. " + "Дома пусты, но в одном — следы костра и дневник.\n" + "\"Озеро просыпается в полнолуние. Не верь голосам. Прячься до рассвета.\"\n");
            System.out.println("Выбор:");
            System.out.println("1 - 🔥 Остаться в доме до рассвета.");
            System.out.println("2 - 🚪 Выйти и идти дальше в туман.");

            int choice = getChoice(2);
            state = (choice == 1) ? State.S_3C : State.S_3D;
        };
    }

    private Runnable sceneTwoC() {
        return () -> {
            System.out.println("\n📍 Сцена 2C: Ожидание у озера");
            System.out.println("Ты остаёшься на месте. Из тумана слышишь детский голос:\n" + "«Помоги мне… пожалуйста…»\n" + "Тебя охватывает страх.\n");
            System.out.println("Выбор:");
            System.out.println("1 - 👧 Пойти на голос.");
            System.out.println("2 - 😨 Убежать прочь в лес.");

            int choice = getChoice(2);
            state = (choice == 1) ? State.S_3E : State.S_3F;
        };
    }

    private Runnable sceneThreeA() {
        return endScene("📍 Сцена 3A: Попытка общения", "Ты кричишь, но свет слепит тебя. Из темноты появляется фигура в противогазе.\nВас усыпляют. Очнулся ты уже в лаборатории. Ты стал частью эксперимента.\nКонцовка: Неудача");
    }

    private Runnable sceneThreeB() {
        return endScene("📍 Сцена 3B: Побег", "Ты бежишь, но спотыкаешься. Прожектор гаснет. Что-то двигается в кустах.\nТы исчез без следа.\nКонцовка: Неудача");
    }

    private Runnable sceneThreeC() {
        return endScene("📍 Сцена 3C: Ожидание", "Ты сидишь у костра. Проходят часы. На рассвете из тумана выходит группа людей — выжившие.\nОни помогают тебе выбраться.\nКонцовка: Победа");
    }

    private Runnable sceneThreeD() {
        return endScene("📍 Сцена 3D: Поиски", "Ты блуждаешь в тумане и выходишь к маяку. Там — старая рация. Связь удаётся установить.\nНа следующий день — эвакуация.\nКонцовка: Победа");
    }

    private Runnable sceneThreeE() {
        return endScene("📍 Сцена 3E: Девочка", "Ты идёшь на голос и видишь девочку в белом. Она улыбается и исчезает в воде.\nВокруг тебя — тени. Ты не можешь двигаться.\nКонцовка: Проклятие озера");
    }

    private Runnable sceneThreeF() {
        return () -> {
            System.out.println("\n📍 Сцена 3F: Побег в лес");
            System.out.println("Ты бежишь прочь. Натыкаешься на подземный люк. Внутри — бункер. Там — доказательства эксперимента: мониторы, карты, журналы.\n");
            System.out.println("Выбор:");
            System.out.println("1 - 💣 Разрушить центр управления.");
            System.out.println("2 - 🧠 Присоединиться к проекту.");

            int choice = getChoice(2);
            state = (choice == 1) ? State.S_4A : State.S_4B;
        };
    }

    private Runnable sceneFourA() {
        return endScene("📍 Сцена 4A: Разрушение", "Ты активируешь самоуничтожение. Озеро начинает светиться. Тьма уходит. Ты спас мир… но погиб.\nКонцовка: Герой");
    }

    private Runnable sceneFourB() {
        return endScene("📍 Сцена 4B: Присоединение", "Ты входишь в проект. Тебе дают новое имя. Ты становишься частью того, что раньше преследовало тебя.\nКонцовка: Тайный участник");
    }


    private Runnable endScene(String title, String text) {
        return () -> {
            System.out.println("\n" + title);
            System.out.println(text);
            isRunning = false;
        };
    }

    private int getChoice(int max) {
        int choice = scanner.nextInt();

        while (choice < 1 || choice > max) {
            System.out.println("Неверный ввод. Введите от 1 до " + max);
            choice = scanner.nextInt();
        }
        return choice;
    }
}
