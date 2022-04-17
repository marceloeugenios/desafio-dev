import React, { useState, useEffect } from "react";
import axios from "axios";
import { Table } from "reactstrap";

import HeaderComponent from "../component/HeaderComponent";
import * as Constantes from "../component/Constantes";

function Extrato() {
  const token = JSON.parse(localStorage.getItem("token"));

  const [extrato, setExtrato] = useState([]);

  const buscar = () => {
    axios
      .get(Constantes.EXTRATO_URL, {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((response) => {
        setExtrato(response.data);
      });
  };

  useEffect(() => {
    if (token) {
      buscar();
    }
  }, [extrato]);

  return (
    <>
      <HeaderComponent />
      <Table>
        <thead>
          <tr>
            <th>Loja</th>
            <th>Entrada</th>
            <th>Saída</th>
            <th>Saldo</th>
          </tr>
        </thead>
        <tbody>
          {extrato.map((extrato) => (
            <tr key={extrato.loja}>
              <td> {extrato.loja}</td>
              <td> {extrato.entrada}</td>
              <td> {extrato.saida}</td>
              <td> {extrato.saldo}</td>
            </tr>
          ))}
        </tbody>
      </Table>
    </>
  );
}

export default Extrato;
