import React from "react";
import ReactDOM from "react-dom/client";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import App from "./App";
import Home from "./pages/Home/Home";
import AddProgram from "./pages/AddProgram/AddProgram";
const router=createBrowserRouter([{path:"/",element:<App/>,children:[
  {index:true,element:<Home/>},
  {path:"add-program",element:<AddProgram/>}
]}]);
ReactDOM.createRoot(document.getElementById("root")!).render(
  <React.StrictMode><RouterProvider router={router}/></React.StrictMode>
);
