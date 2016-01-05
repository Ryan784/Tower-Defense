package Game;

import org.newdawn.slick.Image;

public class DefaultTower {

	private int x, y, width, height, cost, size, damage;
	private int currentUpgrade,  upgradeCost;
	private int[] upgradeDamage = {1,2,4,8,16,32};
	private Image[] upgradeImage = {};
	private String name;
	
	public DefaultTower(int x, int y, int size)
	{
		this.x = x;
		this.y = y;
		this.size = size;
		cost = 100;
		name = "Default Tower";
		damage = 1;
		currentUpgrade = 0;
		upgradeCost = 200;
	}
	
	public void upgrade()
	{
		damage += upgradeDamage[currentUpgrade];
		currentUpgrade++;
		upgradeCost = upgradeCost*2;
	}
	
	public boolean canUpgrade()
	{
		if (currentUpgrade == upgradeDamage.length)
		{
			return false;
		}
		return true;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getCurrentUpgrade() {
		return currentUpgrade;
	}

	public void setCurrentUpgrade(int currentUpgrade) {
		this.currentUpgrade = currentUpgrade;
	}

	public int getUpgradeCost() {
		return upgradeCost;
	}

	public void setUpgradeCost(int upgradeCost) {
		this.upgradeCost = upgradeCost;
	}

	public int[] getUpgradeDamage() {
		return upgradeDamage;
	}

	public void setUpgradeDamage(int[] upgradeDamage) {
		this.upgradeDamage = upgradeDamage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
