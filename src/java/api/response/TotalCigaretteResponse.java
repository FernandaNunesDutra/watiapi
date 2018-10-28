/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.response;

/**
 *
 * @author fernanda
 */
public class TotalCigaretteResponse {
    public double economized;
    public double spent;
    public int smokedTotal;
    public int average;

    public TotalCigaretteResponse(double economized, double spent, int smokedTotal, int average) {
        this.smokedTotal = smokedTotal;
        this.economized = economized;
        this.average = average;
        this.spent = spent;
    }
}
