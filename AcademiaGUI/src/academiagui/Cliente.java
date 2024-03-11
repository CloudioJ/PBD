/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package academiagui;

/**
 *
 * @author claud
 */
public class Cliente {
    public int id;
    public int instrutor;
    public int nutri;
    public int plan;
    public String name;
    public float weight;
    public float height;
    public String date;
    public String email;
    public String phone;
    
    public Cliente(
        int id,
        int instrutor,
        int nutri,
        int plan,
        String name,
        float weight,
        float height,
        String date,
        String email,
        String phone
    ){
        this.id = id;
        this.instrutor = instrutor;
        this.nutri = nutri;
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.date = date;
        this.email = email;
        this.phone = phone;
        this.plan = plan;
    }
}
