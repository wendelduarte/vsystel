import React from 'react';
import { FooterBase } from './styles';

function Footer() {
  return (
    <FooterBase>
      <p>
        © 2021 - Criado por
        {' '}
        <a href="https://github.com/wkinho">
          Wendel Sergio Duarte Junior
        </a>
      </p>
    </FooterBase>
  );
}

export default Footer;