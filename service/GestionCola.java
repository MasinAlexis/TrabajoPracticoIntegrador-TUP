package service;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.Queue;

/*
Clase generica para gestionar las colas de vehiculos en mantenimiento y lavadero.
*/

public class GestionCola<T> {
    private Queue<T> cola;
    private String nombreCola;

    public GestionCola(String nombreCola) {
        this.cola = new LinkedList<>();
        this.nombreCola = nombreCola;
    }

    /**
     * Agregar un elemento a la cola
     */
    public boolean agregar(T elemento) {
        if (cola.contains(elemento)) {
            System.out.println("❌ El elemento ya está en " + nombreCola);
            return false;
        }
        cola.offer(elemento);
        System.out.println("✓ Elemento agregado a " + nombreCola + ". Posición: " + cola.size());
        return true;
    }

    /**
     * Procesar (sacar) el primer elemento de la cola
     */
    public T procesar() {
        if (cola.isEmpty()) {
            System.out.println("❌ No hay elementos en " + nombreCola);
            return null;
        }
        T elemento = cola.poll();
        System.out.println("✓ Elemento procesado de " + nombreCola);
        System.out.println("Elementos restantes: " + cola.size());
        return elemento;
    }

    /**
     * Ver el primer elemento sin sacarlo
     */
    public T verPrimero() {
        return cola.peek();
    }

    /**
     * Obtener el tamaño de la cola
     */
    public int tamanio() {
        return cola.size();
    }

    /**
     * Verificar si la cola está vacía
     */
    public boolean estaVacia() {
        return cola.isEmpty();
    }

    /**
     * Mostrar todos los elementos de la cola
     */
    public void mostrarCola() {
        if (cola.isEmpty()) {
            System.out.println("No hay elementos en " + nombreCola);
            return;
        }

        System.out.println("------------ " + nombreCola + " ------------");
        int posicion = 1;
        for (T elemento : cola) {
            System.out.println("Posición " + posicion++ + ": " + elemento);
        }
        System.out.println("Total: " + cola.size() + " elementos");
    }

    /**
     * Obtener la cola completa (para iteración)
     */
    public Queue<T> obtenerCola() {
        return new LinkedList<>(cola);
    }

    public String getNombreCola() {
        return nombreCola;
    }
}