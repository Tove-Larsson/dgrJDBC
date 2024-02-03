import com.tove.demo.Monster;
import com.tove.demo.Player;
import com.tove.demo.ACombatant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    @DisplayName("Testing levelup")
    public void levelUpTest() {

        Player player = new Player("", 50, 0, 0, 0, 0);

        assertEquals(0, player.getExperience());
        assertEquals(1, player.getLevel());
        assertEquals(50, player.getMaxHealth());
        assertEquals(0, player.getStrength());
        assertEquals(0, player.getIntelligence());
        assertEquals(0, player.getAgility());
        assertEquals(0, player.getBaseDamage());

        player.setExperience(110);

        player.levelUp();

        assertEquals(10, player.getExperience());
        assertEquals(2, player.getLevel());
        assertEquals(52, player.getMaxHealth());
        assertEquals(2, player.getStrength());
        assertEquals(2, player.getIntelligence());
        assertEquals(2, player.getAgility());
        assertEquals(2, player.getBaseDamage());

    }

    @Test
    @DisplayName("Testing if player can lose")
    public void canLoseTest() {

        Player player = new Player("", 10, 0, 0, 0, 0);

        player.receiveDamage(15);

        assertTrue(player.isDead());

    }

    @Test
    @DisplayName("Testing minDamage")
    public void damageTestMin() {


        Player player = new Player("", 10, 2, 2, 0, 5);
        Monster monster = new Monster("", 0, 0, 0, 0, 0, 0);

        int minDamage = 6;

        int damageDealt = player.calculateDamage(monster);

        assertEquals(damageDealt, minDamage);

    }

    @Test
    @DisplayName("Testing maxDamage")
    public void damageTestMax() {

        Player player = new Player("", 10, 2, 100, 0, 5);
        Monster monster = new Monster("", 0, 0, 0, 0, 0, 0);

        int maxDamage = 9;

        int damageDealt = player.calculateDamage(monster);

        assertEquals(damageDealt, maxDamage);


    }



}
