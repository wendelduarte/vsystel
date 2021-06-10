import React from 'react';
import Footer from '../Footer'
import styled from 'styled-components';
import Header from '../Header';

const Main = styled.main`
    text-align: -webkit-center;
    background-color: white;
    color: black;
    flex: 1;
    padding-top: 50px;
    padding-left: 5%;
    padding-right: 5%;
`;

function PageDefault({children}){
    return (
        <>
            <Header/>
                <Main>
                    {children}
                </Main>
            <Footer/>
        </>
    );
}

export default PageDefault;