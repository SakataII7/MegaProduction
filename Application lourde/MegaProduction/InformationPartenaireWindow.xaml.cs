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
using MegaProductionDBLIB;

namespace MegaProduction
{
    /// <summary>
    /// Logique d'interaction pour InformationPartenaireWindow.xaml
    /// </summary>
    public partial class InformationPartenaireWindow : Window
    {
        private MegaCastingsEntities1 db;
        public Client Client { get; set; }

        public InformationPartenaireWindow(MegaCastingsEntities1 context)
        {
            InitializeComponent();
            db = context;
            this.Client = new Client();

            this.DataContext = this;
        }

        private void BTN_Cancel_Click(object sender, RoutedEventArgs e)
        {
            Close();
        }

        private void BTN_Ok_Click(object sender, RoutedEventArgs e)
        {
            this.Client.IsDiffuseur = true;
            this.DialogResult = true;
        }
    }
}
