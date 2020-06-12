package fr.univ_amu.iut.utilitaires;

import javafx.print.Printer;

import java.util.ArrayList;

public class CalculateurPointsFonction {

  private static final int nombrePas = 1000;

  private ArrayList<Basic2DPoint> listePoints;
  private double yMin, yMax;

  public CalculateurPointsFonction(Expression f, double xMin, double xMax) {

    listePoints = new ArrayList<>();

    double dx = (xMax - xMin) / nombrePas;
    yMin = f.valeur(xMin);
    yMax = yMin;

    for (double x = xMin; x <= xMax; x += dx) {
      double y = f.valeur(x);
      listePoints.add(new Basic2DPoint(x, y));
      yMin = Math.min(y, yMin);
      yMax = Math.max(y, yMax);
    }
  }

  public double getYMax() {
    return yMax;
  }

  public double getYMin() {
    return yMin;
  }

  public ArrayList<Basic2DPoint> getListePoints() {
    return listePoints;
  }

  public static void main(String[] args) {
    try {
      Analyseur analyseur = new Analyseur("exp(-x * 0.2) * sin(x)");
      Expression expression = analyseur.analyser();
      System.out.println("f(x) = " + expression);

      double xMin = -0.5D;
      double xMax = 20D;
      CalculateurPointsFonction calculs = new CalculateurPointsFonction(expression, xMin, xMax);
      for (Basic2DPoint point : calculs.listePoints) {
        System.out.println("f(" + point.getX() + ") = " + expression.valeur(point.getX()));
      }
      System.out.println("Minimum de la fonction : " + calculs.getYMin());
      System.out.println("Maximum de la fonction : " + calculs.getYMax());
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }

}
