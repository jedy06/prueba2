package com.mitocode.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

@Entity
public class Paciente {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)//permite generar el ID de la tabla automaticamente
		private Integer idPaciente;

		@Size(min = 3, message = "nombre debe tener minimo 3 caracateres")
		@Column(name = "nombres", nullable = false, length =70)
		private String nombres;
		
		@Size(min = 3, message = "apellido debe tener minimo 3 caracateres")
		@Column(name = "apellidos", nullable = false, length =70)
		private String apellidos;
		
		@Size(min = 3, message = "nombre debe tener minimo 3 caracateres")
		@Column(name = "dni", nullable = false, length =70)
		private String dni;
		
		@Size(min = 8, max = 8, message = "dni debe tener 8 caracateres")
		@Column(name = "direccion", nullable = false, length =150)
		private String direccion;
		
		@Size(min = 9, max = 9, message = "nombre debe tener minimo 3 caracateres")
		@Column(name = "telefono", nullable = false, length =9)
		private String telefono;
		
		@Email
		@Column(name = "email", nullable = false, length =55)
		private String email;
		
		
				
		public String getDni() {
			return dni;
		}
		public void setDni(String dni) {
			this.dni = dni;
		}
		
		public Integer getIdPaciente() {
			return idPaciente;
		}
		public void setIdPaciente(Integer idPaciente) {
			this.idPaciente = idPaciente;
		}
		
		public String getNombres() {
			return nombres;
		}
		public void setNombres(String nombres) {
			this.nombres = nombres;
		}
		
		public String getApellidos() {
			return apellidos;
		}
		public void setApellidos(String apellidos) {
			this.apellidos = apellidos;
		}
		
		public String getTelefono() {
			return telefono;
		}
		public void setTelefono(String telefono) {
			this.telefono = telefono;
		}
		
		public String getEmail() {
			return email;
		}
		
		public void setEmail(String email) {
			this.email = email;
		}
		public String getDireccion() {
			return direccion;
		}
		
		public void setDireccion(String direccion) {
			this.direccion = direccion;
		}
		
		
}
