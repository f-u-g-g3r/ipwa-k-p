import React from 'react'
import ReactDOM from 'react-dom/client'
import './index.css'
import {createBrowserRouter, RouterProvider} from "react-router-dom";
import App from "./App.jsx";
import ErrorPage from "./components/partials/error404.jsx";
import LoginForm, {action, loginLoader} from "./components/loginForm.jsx";
import Home from "./components/home.jsx";
import Posts from "./components/post/posts.jsx";
import PostForm, {actionPostForm} from "./components/post/postForm.jsx";
import {hasAuthority, isAuthLoader} from "./services/AuthService.jsx";
import OnePost from "./components/post/onePost.jsx";
import StudentProfile, {actionStudentProfile} from "./components/profile/studentProfile.jsx";
import CompanyProfile, {actionCompanyProfile} from "./components/profile/companyProfile.jsx";
import TeacherProfile from "./components/profile/teacherProfile.jsx";
import CoordinatorProfile from "./components/profile/coordinatorProfile.jsx";
import EditStudentProfile, {
    actionEditStudent
} from "./components/coordinator/studentsManagement/editStudentProfile.jsx";

const router = createBrowserRouter([
    {
        path: "/",
        element: <App/>,
        errorElement: <ErrorPage/>,
        children: [
            {
                path: "/login",
                loader: loginLoader,
                action: action,
                element: <LoginForm/>
            },
            {
                path: "/home",
                element: <Home/>
            },
            {
                path: "/posts",
                element: <Posts/>,
                loader: isAuthLoader
            },
            {
                path: "/posts/new",
                element: <PostForm/>,
                action: actionPostForm,
            },

            {
                path: "/posts/:id",
                element: <OnePost/>,
                loader: isAuthLoader
            },
            {
                path: "/student/profile",
                element: <StudentProfile/>,
                action: actionStudentProfile,
                loader: isAuthLoader
            },
            {
                path: "/company/profile",
                element: <CompanyProfile/>,
                action: actionCompanyProfile,
                loader: isAuthLoader
            },
            {
                path: "/teacher/profile",
                element: <TeacherProfile/>,
                loader: isAuthLoader
            },
            {
                path: "/coordinator/profile",
                element: <CoordinatorProfile/>,
                loader: isAuthLoader
            },
            {
                path: "edit-student/:id",
                element: <EditStudentProfile/>,
                loader: hasAuthority("COORDINATOR"),
                action: actionEditStudent
            }
        ]
    },
]);

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
      <RouterProvider router={router} />
  </React.StrictMode>,
)
