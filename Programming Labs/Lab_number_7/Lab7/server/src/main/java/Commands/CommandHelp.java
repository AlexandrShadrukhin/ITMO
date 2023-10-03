package Commands;

import WorkModules.Answer;
import WorkModules.Task;

public class CommandHelp   extends Command {
    @Override
    public Answer commandDo(String key, Task task) {
        Answer answer = new Answer();
        answer.setResult("    help : вывести справку по доступным командам\n" +
                "    info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "    show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "    add {element} : добавить новый элемент в коллекцию\n" +
                "    update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "    remove_by_id id : удалить элемент из коллекции по его id\n" +
                "    clear : очистить коллекцию\n" +
                "    save : сохранить коллекцию в файл\n" +
                "    execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "    exit : завершить программу (без сохранения в файл)\n" +
                "    insert_at index {element} : добавить новый элемент в заданную позицию\n" +
                "    remove_last : удалить последний элемент из коллекции\n" +
                "    history : вывести последние 14 команд (без их аргументов)\n" +
                "    average_of_salary : вывести среднее значение поля salary для всех элементов коллекции\n" +
                "    max_by_status : вывести любой объект из коллекции, значение поля status которого является максимальным\n" +
                "    print_field_ascending_end_date : вывести значения поля endDate всех элементов в порядке возрастания");
        return answer;
    }
}
