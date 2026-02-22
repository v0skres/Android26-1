import parser.CsvParser;
import resolver.Resolver;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils
import org.jfree.data.category.DefaultCategoryDataset;
import java.io.File

fun main(args: Array<String>) {
    val players = CsvParser.parseFile("./src/main/resources/fakePlayers.csv");
    val resolver = Resolver(players);
    println("Без агенства: " + resolver.getCountWithoutAgency().toString());
    println("Наибольшее число голов из числа защитников: " + resolver.getBestScorerDefender().toString());
    println("Название позиции самого дорогого немецкого игрока: " + resolver.getTheExpensiveGermanPlayerPosition());
    println("Команда с наибольшим числом удалений на одного игрока: " + resolver.getTheRudestTeam().name);

    val dataset = DefaultCategoryDataset();
    val teams = players.groupBy { it.team }
        .map { (_, p) -> p.sumOf { it.transferCost } to p[0].team.name }
        .sortedBy { it.first };

    for (team in teams) {
        dataset.addValue(team.first, team.second, team.second);
    }
    val barChart = ChartFactory.createBarChart("Топ-10 команд с наивысшей суммарной трансферной стоимостью", "Команда", "Стоимость", dataset);
    ChartUtils.saveChartAsPNG(File("table.png"), barChart, 640, 480);
}