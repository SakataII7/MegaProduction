using MegaProductionDBLIB;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace MegaProduction
{
    /// <summary>
    /// Logique d'interaction pour InformationTypeContratWindow.xaml
    /// </summary>
    public partial class InformationTypeContratWindow : Window
    {
        private MegaCastingsEntities db;
        public TypeContrat TypeContrat { get; set; }

        public InformationTypeContratWindow(MegaCastingsEntities context, TypeContrat typeContrat)
        {
            InitializeComponent();
            db = context;
            this.TypeContrat = typeContrat;
            this.DataContext = this;
        }

        private void BTN_Cancel_Click(object sender, RoutedEventArgs e)
        {
            Close();
        }

        private void BTN_Ok_Click(object sender, RoutedEventArgs e)
        {
            this.DialogResult = true;
        }
    }
}
