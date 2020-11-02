/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Eemeli
 */
public class StatisticsTest {
 
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka k‰ytt‰‰ "stubia"
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void searchReturnsTheRightPlayer() {
        assertThat(stats.search("G").getName(), is("Gretzky"));
    }
    
    @Test
    public void searchReturnsNullWhenPlayerDoesNotExist() {
        assertThat(stats.search("NotAPlayer"), nullValue());
    }
    
    @Test
    public void teamReturnsTheRightTeam() {
        List<Player> team = stats.team("EDM");
        assertThat(team.size(), is(3));
        assertThat(team.get(0).getName(), is("Semenko"));
        assertThat(team.get(1).getName(), is("Kurri"));
        assertThat(team.get(2).getName(), is("Gretzky"));
    }
    
    @Test
    public void topScorersReturnsTheRighPlayers() {
        List<Player> top3 = stats.topScorers(3);
        assertThat(top3.size(), is(3));
        assertThat(top3.get(0).getName(), is("Gretzky"));
        assertThat(top3.get(1).getName(), is("Lemieux"));
        assertThat(top3.get(2).getName(), is("Yzerman"));
    }
}
