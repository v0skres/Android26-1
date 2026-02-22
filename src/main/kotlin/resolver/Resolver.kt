package resolver
import model.*;

class Resolver(val players: List<Player>): IResolver {
    override fun getCountWithoutAgency(): Int {
        return players.count { it.agency.isEmpty() };
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        return players.filter { it.position == Position.DEFENDER }
            .map { it.name to it.goals }
            .maxBy { it.second };
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        return players.filter { it.nationality == "Germany" }
            .maxBy { it.transferCost }
            .position
            .displayName;
    }

    override fun getTheRudestTeam(): Team {
        return players.groupBy { it.team }
            .maxBy { (_, p) -> p.sumOf { it.redCards }.toFloat() / p.size }
            .key;
    }
}