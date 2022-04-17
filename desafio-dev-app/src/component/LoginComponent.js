import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import * as Constantes from "../component/Constantes";

import { Form, FormGroup, Label, Input, Button } from "reactstrap";

function Login() {
  const [user, setUser] = useState();
  const [senha, setSenha] = useState();
  const [error, setError] = useState(false);

  const navigate = useNavigate();

  const handleChange = (event) => {
    if (event.target.name === "usuario") {
      setUser(event.target.value);
    } else {
      setSenha(event.target.value);
    }
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    setError(false);

    const credenciais = { usuario: user, senha: senha };

    axios
      .post(Constantes.LOGIN_URL, credenciais)
      .then((response) => {
        localStorage.setItem(
          "token",
          JSON.stringify(response.data["access_token"])
        );
        localStorage.setItem(
          "refresh_token",
          JSON.stringify(response.data["refresh_token"])
        );
        navigate("/extrato", { replace: true });
      })
      .catch((error) => {
        setError(true);
      });
  };

  return (
    <Form onSubmit={handleSubmit}>
      <FormGroup className="mb-3">
        <Label>Usuário</Label>
        {error && (
          <>
            <br />
            <span style={{ color: "red" }}>Usuário ou senha inválidos</span>
          </>
        )}
        <Input
          type="text"
          name="usuario"
          placeholder="Usuário"
          required
          onChange={handleChange}
        />

        <Label>Senha</Label>
        <Input
          type="password"
          name="senha"
          placeholder="Senha"
          required
          onChange={handleChange}
        />
      </FormGroup>
      <Button variant="outline-primary" type="submit" size="sm">
        Acessar
      </Button>
    </Form>
  );
}

export default Login;
