import React from 'react';
import { Link } from 'react-router-dom';
import Logo from '../../assets/img/Logo.png';
import './styles.css';

function Header(){
    return (
        <div className='header'>
            <Link to='/'>
                <img className='logo' src={Logo} alt='Vsystel logo' />
            </Link>
        </div>
    );
}

export default Header;