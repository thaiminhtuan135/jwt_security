import Head from 'next/head'
import Image from 'next/image'
import styles from '@/styles/Home.module.css'
import {ReactElement, useEffect, useState} from 'react'
import axios from "axios";
import {Layout} from './component/layout';
import Token from './auth/Token';
interface Authenticate{
    email: String;
    password: String;
}
interface User{
    firstname: String;
    lastname: String;
    email: String;
    password: String;
}


const url = "http://localhost:8080/api/v1/auth/register";
function Home() {
    const payload = (token) => {
        const payload = token.split(".")[1];
        return decode(payload);
    }
    const decode = (payload) => {
        return JSON.parse(atob(payload));
    }
    const token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsYWxhbGFsYWxhbGxhQGdtYWlsLmNvbSIsInJvbGVzIjpbeyJhdXRob3JpdHkiOiJVU0VSIn1dLCJpYXQiOjE2Nzk2MzMwMjQsImV4cCI6MTY3OTYzNDQ2NH0.PWQjYAqbtNvLqS5JkyTE1IIoyr8cj-MYAx6fAqb_b_k";
    console.log(payload(token));

    const [user, setUser] = useState<User>(
        {firstname : "tuan" , lastname : "minh" ,email: "lalalalalalla@gmail.com" , password : "123456"}
    );
    useEffect(() => {
        //  axios
        //     .post(url, user)
        //     .then((res) => {
        //         console.log(res);
        //         console.log(res.data)
        //
        //     }).catch(() => {
        //
        // });
        // axios
        //     .get('http://localhost:8080/api/v1/auth/message')
        //     .then((res) => {
        //         console.log(res.data)
        //
        //     }).catch(() => {
        // });
    }, []);

    return (
    <>
      <div className={"font-bold"}>tuan</div>
      <div>dsadsa</div>
    </>
  )
}

Home.getLayout = function getLayout(page: ReactElement) {
  return (
    <Layout>
      {page}
    </Layout>
  )
}

export default Home
