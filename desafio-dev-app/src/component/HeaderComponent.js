import React, { useEffect } from "react";
import { Nav, NavItem, NavLink } from "reactstrap";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import * as Constantes from "../component/Constantes";

import "bootstrap/dist/css/bootstrap.min.css";

function Header() {
  const navigate = useNavigate();

  const token = JSON.parse(localStorage.getItem("token"));
  const refreshToken = JSON.parse(localStorage.getItem("refresh_token"));

  function irParaExtrato() {
    navigate("/extrato", { replace: true });
  }

  function irParaUpload() {
    navigate("/upload", { replace: true });
  }

  function verificarIsLogado() {
    if (!token) {
      navigate("/", { replace: true });
    }
  }

  const handleLogout = () => {
    localStorage.clear();
    axios
      .post(Constantes.LOGOUT_URL + "?refresh_token=" + refreshToken, null)
      .then((response) => {
        navigate("/", { replace: true });
        console.log("Logout: " + response.data["mensagem"]);
      });
  };

  useEffect(() => {
    verificarIsLogado();
  }, []);

  return (
    <div>
      <Nav pills>
        <NavItem>
          <NavLink href="#" onClick={irParaExtrato}>
            Extrato
          </NavLink>
        </NavItem>

        <NavItem>
          <NavLink href="#" onClick={irParaUpload}>
            Upload
          </NavLink>
        </NavItem>
        <NavItem>
          <NavLink active href="#" onClick={handleLogout}>
            Sair
          </NavLink>
        </NavItem>
      </Nav>
    </div>
  );
}

export default Header;
