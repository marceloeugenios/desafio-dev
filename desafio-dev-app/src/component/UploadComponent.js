import axios from "axios";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

import * as Constantes from "../component/Constantes";

import {
  Form,
  FormGroup,
  Input,
  Button,
  ListGroup,
  ListGroupItem,
  Modal,
  ModalFooter,
  ModalHeader,
  ModalBody,
} from "reactstrap";

import HeaderComponent from "../component/HeaderComponent";

function Upload() {
  const token = JSON.parse(localStorage.getItem("token"));

  // Modal open state
  const [modal, setModal] = useState(false);
  const [arquivo, setArquivo] = useState({
    id: 0,
    nome: "",
    dataUpload: null,
  });

  // Toggle for Modal
  const toggle = () => setModal(!modal);

  const [file, setFile] = useState();
  const navigate = useNavigate();

  function handleChange(event) {
    setFile(event.target.files[0]);
  }

  function handleSubmit(event) {
    event.preventDefault();
    const url = Constantes.UPLOAD_URL;
    const formData = new FormData();
    formData.append("arquivo", file);
    const config = {
      headers: {
        "content-type": "multipart/form-data",
        Authorization: `Bearer ${token}`,
      },
    };
    axios
      .post(url, formData, config)
      .then((response) => {
        setArquivo(response.data);
        setModal(true);
      })
      .catch(function(error) {
        if (error.response) {
          console.log(error.response.data);
          console.log(error.response.status);
          console.log(error.response.headers);
        }
      });
  }

  function handleOk() {
    setModal(false);
    navigate("/extrato", { replace: true });
  }

  return (
    <>
      <HeaderComponent />
      <Form onSubmit={handleSubmit}>
        <FormGroup>
          <ListGroup>
            <ListGroupItem className="d-flex">
              <Input type="file" onChange={handleChange} />

              <Button type="submit" style={{ marginLeft: "10px" }}>
                Enviar
              </Button>
            </ListGroupItem>
          </ListGroup>
        </FormGroup>

        <div>
          <Modal isOpen={modal} toggle={toggle}>
            <ModalHeader toggle={toggle}>
              CNAB Carregado com sucesso
            </ModalHeader>
            <ModalBody>
              ID: {arquivo.id}
              <br />
              Nome: {arquivo.nome} <br />
              Data de Upload: {arquivo.dataUpload}
            </ModalBody>
            <ModalFooter>
              <Button color="primary" onClick={handleOk}>
                OK
              </Button>
            </ModalFooter>
          </Modal>
        </div>
      </Form>
    </>
  );
}

export default Upload;
