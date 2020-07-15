package sandshiph.warehouse;

import java.util.HashMap;
public class Warehouse {
    private HashMap<Material, Integer> inventory;

    public Warehouse() {
    }

    public Warehouse(HashMap<Material, Integer> inventory) {
        this.inventory = inventory;
    }

    public boolean addMaterials(HashMap<Material, Integer> materials){
        materials.
                keySet().
                stream().
                filter(each -> !inventory.containsKey(each)).
                forEach(each -> inventory.put(each, materials.get(each)));
        materials.
                keySet().
                stream().
                filter(each -> inventory.containsKey(each)).
                forEach(each -> {
                    if (materials.get(each)+inventory.get(each) < each.getMaxCapacity())
                        inventory.replace(each, materials.get(each)+inventory.get(each));
                });
        return true;
    }
    public boolean removeMaterials(HashMap<Material, Integer> materials){
        materials.
                keySet().
                stream().
                filter(each -> inventory.containsKey(each)).
                forEach(each -> {
                    if (materials.get(each) >= inventory.get(each))
                        inventory.remove(each);
                    else
                        inventory.replace(each, inventory.get(each)-materials.get(each));
                });
        return true;
    }
    public boolean transferMaterials(Warehouse warehouse, HashMap<Material, Integer> materials){
        removeMaterials(materials);
        warehouse.addMaterials(materials);
        return true;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "inventory=" + inventory +
                '}';
    }
}
