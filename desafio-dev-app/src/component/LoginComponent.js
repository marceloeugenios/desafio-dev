import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import * as Constantes from "../component/Constantes";

import { Form, FormGroup, Label, Input, Button } from "reactstrap";

function Login() {
  const [login, setLogin] = useState("desafio");
  const [senha, setSenha] = useState("12345");

  const navigate = useNavigate();

  const changeHandler = (event) => {
    if (event.target.name === "usuario") {
      setLogin(event.target.value);
    } else {
      setSenha(event.target.value);
    }
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    const credenciais = { usuario: login, senha: senha };

    axios.post(Constantes.LOGIN_URL, credenciais).then((response) => {
      localStorage.setItem(
        "token",
        JSON.stringify(response.data["access_token"])
      );
      localStorage.setItem(
        "refresh_token",
        JSON.stringify(response.data["refresh_token"])
      );
      navigate("/extrato", { replace: true });
    });
  };

  return (
    <Form onSubmit={handleSubmit}>
      <FormGroup className="mb-3">
        <Label>Usuário</Label>
        <Input
          type="text"
          name="usuario"
          placeholder="Usuário"
          value="desafio"
          required
          onChange={changeHandler}
        />

        <Label>Senha</Label>
        <Input
          type="password"
          name="senha"
          placeholder="Senha"
          value="12345"
          required
          onChange={changeHandler}
        />
      </FormGroup>
      <Button variant="outline-primary" type="submit" size="sm">
        Acessar
      </Button>
    </Form>
  );
}

export default Login;
