import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";

import ExtratoComponent from "./component/ExtratoComponent";
import UploadComponent from "./component/UploadComponent";
import LoginComponent from "./component/LoginComponent";

import "bootstrap/dist/css/bootstrap.min.css";

function App() {
  return (
    <div style={{ maxWidth: "30rem", margin: "4rem auto" }}>
      <BrowserRouter>
        <Routes>
          <Route exact path="/" element={<LoginComponent />} />
          <Route path="/extrato" element={<ExtratoComponent />} />
          <Route path="/upload" element={<UploadComponent />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
